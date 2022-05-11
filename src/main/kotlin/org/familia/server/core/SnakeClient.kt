package org.familia.server.core

import org.familia.client.common.request.Request
import org.familia.client.common.request.board.RollDiceRequest
import org.familia.client.common.request.match.Match
import org.familia.client.common.request.user.User
import org.familia.client.common.response.Response
import org.familia.client.common.response.board.BoardResponse
import org.familia.client.common.response.board.PlayerTurnResponse
import org.familia.client.common.response.user.UserResponse
import org.familia.client.common.status.Status
import org.familia.server.contract.ClientConnectionContract
import org.familia.server.contract.MatchQueueContract
import org.familia.server.getSocketKey
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import java.net.SocketException

class SnakeClient(
    private val socket: Socket,
    private val connectionContract: ClientConnectionContract,
    private val matchQueueContract: MatchQueueContract
) {

    private val inputStream = ObjectInputStream(socket.getInputStream())
    private val outputStream = ObjectOutputStream(socket.getOutputStream())

    private var isConnected = false

    var username = ""
        private set

    fun serve() = try {
        while (true) {
            if (!isConnected) {
                break
            }
            val request = inputStream.readObject() as Request
            if (request.payload is Match) {
                requestMatch(request.payload as Match)
                break
            }
        }

    } catch (e: SocketException) {
        disconnect()
    }

    fun connect(): Boolean {
        try {
            connectionContract.onClientConnected(socket.getSocketKey(), this)
            while (true) {
                val request = inputStream.readObject() as Request
                val tempUsername = (request.payload as User).username

                if (connectionContract.isUsernameTaken(tempUsername)) {
                    outputStream.writeObject(
                        Response(
                            "Username is already taken",
                            Status.Error,
                            UserResponse("")
                        )
                    )
                } else {
                    isConnected = true
                    username = tempUsername
                    outputStream.writeObject(
                        Response(
                            "Successfully logged in",
                            Status.Success,
                            UserResponse(tempUsername)
                        )
                    )
                    return true
                }
            }
        } catch (e: Exception) {
            disconnect()
            return false
        }
    }

    private fun requestMatch(request: Match) {
        matchQueueContract.onMatchRequested(this, request.type)
    }

    fun sendBoard(status: Status, board: BoardResponse) {
        val message = if (status == Status.Success) {
            "Your board is ready to play"
        } else {
            "Something went wrong"
        }

        with(outputStream) {
            writeObject(Response(
                message,
                status,
                board
            ))
            flush()
        }
    }

    fun sendTurnResponse(response: PlayerTurnResponse) {
        outputStream.writeObject(Response(
            "Your turn to ngocok",
            Status.Success,
            response
        ))
    }

    fun awaitRollRequest(): RollDiceRequest {
        return (inputStream.readObject() as Request).payload as RollDiceRequest
    }

    private fun disconnect() {
        isConnected = false
        connectionContract.onClientDisconnected(socket.getSocketKey(), this)
        socket.close()
    }

    fun getSocketKey() = socket.getSocketKey()

}