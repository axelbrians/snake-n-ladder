package org.familia.server.core

import org.familia.client.common.request.Request
import org.familia.client.common.request.match.Match
import org.familia.client.common.request.user.User
import org.familia.client.common.response.Response
import org.familia.client.common.response.board.BoardResponse
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
        connect()
        while (true) {
            println("masuk loop")
            if (!isConnected) {
                break
            }
            val request = inputStream.readObject() as Request
            println(request)
            if(request.payload is Match) {
                println("Test")
                requestMatch(request.payload as Match)
            }
            //requestMatch()

        }

    } catch (e: SocketException) {
        disconnect()
    }

    private fun connect() = try {
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
                break
            }
        }
    } catch (e: Exception) {
        throw SocketException("Connection Reset")
    }

    private fun requestMatch(request: Match) {

        matchQueueContract.onMatchRequested(request.user.username, request.type)
    }

    fun sendBoard(board: BoardResponse) {
        with(outputStream) {
            writeObject(board)
            flush()
        }
    }

    private fun disconnect() {
        isConnected = false
        connectionContract.onClientDisconnected(socket.getSocketKey(), this)
        socket.close()
    }

}