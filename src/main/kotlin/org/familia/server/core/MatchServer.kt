package org.familia.server.core

import kotlinx.coroutines.*
import org.familia.client.apps.networks.request.user.Message
import org.familia.client.apps.networks.response.board.BoardResponse
import org.familia.client.apps.networks.response.board.EndMatchResponse
import org.familia.client.apps.networks.response.board.PlayerMoveResponse
import org.familia.client.apps.networks.response.board.PlayerTurnResponse
import org.familia.client.apps.networks.response.user.MessageResponse

class MatchServer(
    private val boardResponse: BoardResponse,
    private val clients: List<SnakeClient>
) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val finishedPlayerToEndTheGame = boardResponse.players.size - 1
    private var finishedPlayers = mutableSetOf<String>()
    private val clientJobs = hashMapOf<String, Job>()

    private var currentUser = ""
    private var isBrake = false

    suspend fun startMatch() = try {
        startThread()
        matchLoop@ while (true) {
            for ((username, position) in boardResponse.players) {

                // check gamenya udh tamat/belum
                if (finishedPlayers.size >= finishedPlayerToEndTheGame) {
                    boardResponse.players.forEach {
                        finishedPlayers.add(it.first)
                    }
                    break@matchLoop
                } else if (position == 100) {
                    continue
                }

                //ngirim response ke player buat roll dice
                currentUser = username
                clients.find { it.username == username }?.let {
                    it.sendTurnResponse(
                        PlayerTurnResponse(
                            username
                        )
                    )
                    isBrake = true
                }

                while (true) {
                    if (!isBrake) break
                    delay(500)
                }
            }
        }
        endMatch()
    } catch (e: Exception) {
        println(e.stackTrace.toString())
    }

    private fun updatePlayerPosition(username: String, diceRoll: Int) {
        var position = (boardResponse.players.find {
            it.first == username
        }?.second ?: return)

        if (position + diceRoll > 100) {
            position = 100 - ((position + diceRoll) % 100)
        } else if (position + diceRoll == 100) {
            position += diceRoll
            finishedPlayers.add(username)
        } else {
            position += diceRoll
        }

        println("$username current position: $position")

        boardResponse.ladders.forEach {
            if (it.first == position) {
                position = it.second
                println("$username climbing ladder from ${it.first} to ${it.second}")
            }
        }

        boardResponse.snakes.forEach {
            if (it.first == position) {
                position = it.second
                println("$username bitten by snake from ${it.first} to ${it.second}")
            }
        }

        boardResponse.players = boardResponse.players.map { // Update players data with new List<*>
            if (it.first == username) {
                Pair(username, position)
            } else {
                it
            }
        }
    }

    private fun broadcastMove(username: String, diceRoll: Int, playerMoveResponse: PlayerMoveResponse) {
        updatePlayerPosition(username, diceRoll)
        clients.forEach { client: SnakeClient ->
            client.sendMoveResponse(playerMoveResponse)
        }
        isBrake = false
    }

    private fun broadcastMessage(request: Message) {
        val message = MessageResponse(
            request.sender,
            request.text
        )
        clients.forEach { client: SnakeClient ->
            client.sendInGameMessage(message)
        }
    }

    private fun getCurrentPlayerUsername(): String {
        return currentUser
    }

    private fun endMatch() {
        val endMatchResponse = EndMatchResponse(finishedPlayers.toList())
        clients.forEach { client: SnakeClient ->
            client.sendEndMatchResponse(endMatchResponse)
        }
    }

    private fun startThread() {
        clients.forEach { client: SnakeClient ->
            clientJobs[client.getSocketKey()] = coroutineScope.launch {
                client.inGameServe(
                    this@MatchServer::broadcastMove,
                    this@MatchServer::broadcastMessage,
                    this@MatchServer::getCurrentPlayerUsername
                )
            }
        }
    }
}