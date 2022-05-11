package org.familia.server.core

import org.familia.client.common.response.board.BoardResponse
import org.familia.client.common.response.board.PlayerTurnResponse

class MatchServer(
    private val boardResponse: BoardResponse,
    private val clients: List<SnakeClient>
) {

    fun startMatch() {
        val playerTurn = boardResponse.players.map { it.first }

        while (true) {
            playerTurn.forEach { username ->
                clients.find { it.username == username }?.let {
                    it.sendTurnResponse(PlayerTurnResponse(username))

                    val request = it.awaitRollRequest() // waiting for asked player to roll the dice

                    println("$username hit ${request.hitArea} bar")
                }
            }
        }
    }

    private fun endMatch() {

    }
}