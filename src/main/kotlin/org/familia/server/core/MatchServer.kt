package org.familia.server.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.familia.client.apps.networks.response.board.BoardResponse
import org.familia.client.apps.networks.response.board.EndMatchResponse
import org.familia.client.apps.networks.response.board.PlayerMoveResponse
import org.familia.client.apps.networks.response.board.PlayerTurnResponse
import kotlin.io.path.createTempDirectory

class MatchServer(
    private val boardResponse: BoardResponse,
    private val clients: List<SnakeClient>
) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val finishedPlayerToEndTheGame = boardResponse.players.size - 1
    private var finishedPlayers = mutableSetOf<String>()

    fun startMatch() {
        matchLoop@ while (true) {
            for ((username, position) in boardResponse.players) {
                if (finishedPlayers.size >= finishedPlayerToEndTheGame) {
                    boardResponse.players.forEach {
                        finishedPlayers.add(it.first)
                    }
                    break@matchLoop
                } else if (position == 100) {
                    continue
                }
                clients.find { it.username == username }?.let {
                    it.sendTurnResponse(
                        PlayerTurnResponse(
                            username
                        )
                    )

                    val request = it.awaitRollRequest() // waiting for asked player to roll the dice

                    println("$username hit ${request.hitArea} bar")

                    val diceRoll = rollDiceWithHitArea(request.hitArea)
                    val playerMoveResponse = PlayerMoveResponse(username, diceRoll)

                    updatePlayerPosition(username, diceRoll)
                    clients.forEach { client: SnakeClient ->
                        client.sendMoveResponse(playerMoveResponse)
                    }
                }
            }
        }
        endMatch()
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

        boardResponse.players = boardResponse.players.map { // Update players data with new List<*>
            if (it.first == username) {
                Pair(username, position)
            } else {
                it
            }
        }
    }

    private fun rollDiceWithHitArea(hitArea: Int): Int {
        // TODO:(Logic dice roll with hitArea here)

        return hitArea
    }

    private fun endMatch() {
        val endMatchResponse = EndMatchResponse(finishedPlayers.toList())
        clients.forEach { client: SnakeClient ->
            client.sendEndMatchResponse(endMatchResponse)
        }
    }
}