package org.familia.server.core

import kotlinx.coroutines.*
import org.familia.client.apps.networks.request.match.MatchType
import org.familia.client.apps.networks.response.board.BoardResponse
import org.familia.client.apps.networks.status.Status
import org.familia.server.contract.ClientConnectionContract
import org.familia.server.contract.MatchQueueContract
import org.familia.server.getSocketKey
import org.familia.server.helper.LoggerHelper
import java.io.BufferedOutputStream
import java.net.ServerSocket
import java.net.Socket

class SnakeServer: ClientConnectionContract, MatchQueueContract {

    private val logger = LoggerHelper()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val matchCoroutineScope = CoroutineScope(Dispatchers.IO)

    private val clientJobs = hashMapOf<String, Job>()
    private val clients = hashMapOf<String, SnakeClient>()
    private val matchJobs = hashMapOf<String, Job>()
    private val twoPlayerMatchQueue: MutableList<SnakeClient> = mutableListOf()
    private val fourPlayerMatchQueue: MutableList<SnakeClient> = mutableListOf()

    fun serve(port: Int) {
        val server = ServerSocket(port)
        while (true) {
            logger.waitingClient()

            val socket: Socket = server.accept()

            logger.clientConnected(socket)

            val job = coroutineScope.launch {
                val client = SnakeClient(
                    socket,
                    this@SnakeServer,
                    this@SnakeServer
                )

                if (client.connect()) {
                    client.serve()
                }
            }

            checkClientIPConnection(socket, job)
        }
    }

    private fun checkClientIPConnection(socket: Socket, job: Job) {
        val prevJobs = clientJobs[socket.getSocketKey()]
        if (prevJobs != null) {
            job.cancel()
            with(BufferedOutputStream(socket.getOutputStream())) {
                write("Multiple connection within same IP is not allowed".toByteArray())
                flush()
            }
            socket.close()
        } else {
            clientJobs[socket.getSocketKey()] = job
        }
    }

    override fun onClientConnected(key: String, client: SnakeClient) {
        clients[key] = client
    }

    override fun onClientDisconnected(key: String, client: SnakeClient) {
        try {
            logger.clientDisconnected(key, client.username)
            clientJobs[key]?.cancel()
            clientJobs.remove(key)
            clients.remove(key)
        } catch (e: Exception) {
            logger.logException(e)
        }
    }

    override fun isUsernameTaken(username: String): Boolean {
        clients.forEach {
            if (it.value.username == username) {
                return true
            }
        }

        return false
    }

    override fun onMatchRequested(client: SnakeClient, matchType: MatchType) {
        if (matchType == MatchType.TwoPlayer) {
            twoPlayerMatchQueue.add(client)
            println("${client.username} enter 2 player match, total user: " + twoPlayerMatchQueue.size)
            if (twoPlayerMatchQueue.size >= 2) {
                try {
                    logger.clientDisconnected(client.getSocketKey(), client.username)
                } catch (e: Exception) {
                    logger.logException(e)
                }
                createMatch(matchType)
            }
        } else if (matchType == MatchType.FourPlayer) {
            fourPlayerMatchQueue.add(client)
            println("${client.username} enter 4 player match, total user: " + fourPlayerMatchQueue.size)
            if (fourPlayerMatchQueue.size >= 4) {
                try {
                    logger.clientDisconnected(client.getSocketKey(), client.username)
                } catch (e: Exception) {
                    logger.logException(e)
                }
                createMatch(matchType)
            }
        }
    }

    private fun createMatch(matchType: MatchType) {
        val playerCount = if (matchType == MatchType.TwoPlayer) 2 else 4
        val tempRoomQueue = mutableListOf<String>()

        repeat (playerCount) {
            tempRoomQueue.add(twoPlayerMatchQueue[it].username)
        }

        val boardResponse = BoardResponse(
            tempRoomQueue.shuffled().map { Pair(it, 0) })
        val playingClients = mutableListOf<SnakeClient>()

        logger.logMatchedPlayer(tempRoomQueue)

        repeat(playerCount) {
            with(twoPlayerMatchQueue.first()) {
                playingClients.add(this)
                sendBoardResponse(Status.Success, boardResponse)
            }
            twoPlayerMatchQueue.removeFirst()
        }

        println(playingClients.map { it.username })

        playingClients.forEach {
            clientJobs[it.getSocketKey()]?.cancel()
            clientJobs.remove(it.getSocketKey())
        }

        matchJobs[tempRoomQueue.joinToString { "" }] = matchCoroutineScope.launch {
            MatchServer(
                boardResponse,
                playingClients
            ).startMatch()

            playingClients.forEach { client: SnakeClient ->
                clientJobs[client.getSocketKey()] = coroutineScope.launch {
                    client.serve()
                }
            }
        }
    }

}