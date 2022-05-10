package org.familia.server.contract

import org.familia.server.core.SnakeClient

interface ClientConnectionContract {

    fun onClientConnected(key: String, client: SnakeClient)

    fun onClientDisconnected(key: String, client: SnakeClient)

    fun isUsernameTaken(username: String): Boolean
}