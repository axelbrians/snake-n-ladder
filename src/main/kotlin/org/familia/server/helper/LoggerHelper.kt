package org.familia.server.helper

import org.familia.server.getSocketKey
import java.net.Socket
import java.util.logging.Level
import java.util.logging.Logger

class LoggerHelper {

    private val logger = Logger.getLogger("debug")

    fun waitingClient() {
        println("= = = Waiting for client to connect = = =")
//        logger.log(Level.INFO, "= = = Waiting for client to connect = = =")
    }

    fun clientConnected(socket: Socket) {
        println("= = = ${socket.getSocketKey()} Connected = = =")
//        logger.log(Level.INFO, "= = = ${socket.getSocketKey()} Connected = = =")
    }

    fun clientDisconnected(key: String, username: String) {
        println("= = = $key as ($username) Disconnected = = =")
    }

    fun logException(e: Exception) {
        logger.log(
            Level.SEVERE,
            "= = = Exception = = =\n$e\n= = = = = = =\n"
        )
    }
}