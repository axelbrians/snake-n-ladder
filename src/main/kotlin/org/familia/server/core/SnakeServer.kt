package org.familia.server.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.familia.client.common.response.board.BoardResponse
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
    private val clientJobs = hashMapOf<String, Job>()
    private val clients = hashMapOf<String, SnakeClient>()
    private val room2Queue: MutableList<String> = mutableListOf()
    private val room4Queue: MutableList<String> = mutableListOf()

    fun serve(port: Int) {

        val server = ServerSocket(port)


        while (true) {
            logger.waitingClient()

            val socket: Socket = server.accept()

            logger.clientConnected(socket)

            val job = coroutineScope.launch {
                SnakeClient(socket, this@SnakeServer, this@SnakeServer).serve()
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

    override fun onMatchRequested(username: String, room: Int) {
        if(room == 1) {
            room2Queue.add(username)
            println("User enter 2 player room, total user: " + room2Queue.size)
            if(room2Queue.size >= 2) {
                var tempRoom2Queue: MutableList<String> = mutableListOf()
                tempRoom2Queue.add(room2Queue[0])
                tempRoom2Queue.add(room2Queue[1])
                clients.forEach{(_, client) ->
                    if(room2Queue[0] == client.username || room2Queue[1] == client.username) {
                        client.sendBoard(BoardResponse(tempRoom2Queue))
                    }
                }
                room2Queue.removeFirst()
                room2Queue.removeFirst()
                println("Matched")
            }

        }
        else if(room == 2) {
            room4Queue.add(username)
            if(room4Queue.size >= 4) {
                var tempRoom4Queue: MutableList<String> = mutableListOf()
                tempRoom4Queue.add(room4Queue[0])
                tempRoom4Queue.add(room4Queue[1])
                tempRoom4Queue.add(room4Queue[2])
                tempRoom4Queue.add(room4Queue[3])
                clients.forEach{(_, client) ->
                    if(room4Queue[0] == client.username || room4Queue[1] == client.username
                        || room4Queue[2] == client.username || room4Queue[3] == client.username) {
                        client.sendBoard(BoardResponse(tempRoom4Queue))
                    }
                }
                room4Queue.removeFirst()
                room4Queue.removeFirst()
                room4Queue.removeFirst()
                room4Queue.removeFirst()
            }
        }
    }

}