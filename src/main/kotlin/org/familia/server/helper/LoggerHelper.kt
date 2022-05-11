package org.familia.server.helper

import org.familia.server.getSocketKey
import java.net.Socket
import java.util.logging.Level
import java.util.logging.Logger

class LoggerHelper {

    private val logger = Logger.getLogger("debug")

    fun waitingClient() {
        println("= = = Waiting for client to connect = = =")
    }

    fun clientConnected(socket: Socket) {
        println("= = = ${socket.getSocketKey()} Connected = = =")
    }

    fun clientDisconnected(key: String, username: String) {
        println("= = = $key as ($username) Disconnected = = =")
    }

    fun logMatchedPlayer(players: List<String>) {
        println("${players.joinToString(separator = ", ")} matched in a match")
    }

    fun logException(e: Exception) {
        logger.log(
            Level.SEVERE,
            "= = = Exception = = =\n$e\n= = = = = = =\n"
        )
    }
}