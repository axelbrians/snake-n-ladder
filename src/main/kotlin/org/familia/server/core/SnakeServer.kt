package org.familia.server.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.familia.server.contract.ClientConnectionContract
import org.familia.server.getSocketKey
import org.familia.server.helper.LoggerHelper
import java.io.BufferedOutputStream
import java.net.ServerSocket
import java.net.Socket

class SnakeServer: ClientConnectionContract {

    private val logger = LoggerHelper()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val clientJobs = hashMapOf<String, Job>()
    private val clients = hashMapOf<String, SnakeClient>()

    fun serve(port: Int) {

        val server = ServerSocket(port)


        while (true) {
            logger.waitingClient()

            val socket: Socket = server.accept()

            logger.clientConnected(socket)

            val job = coroutineScope.launch {
                SnakeClient(socket, this@SnakeServer).serve()
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

}