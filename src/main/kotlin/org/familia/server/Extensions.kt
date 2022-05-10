package org.familia.server

import java.net.Socket

fun Socket.getSocketKey(): String {
    return this.remoteSocketAddress.toString()
}