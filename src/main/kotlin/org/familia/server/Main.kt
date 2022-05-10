package org.familia.server

import org.familia.server.core.SnakeServer

fun main() {

    val port = 6969
    with(SnakeServer()) {
        serve(port)
    }
}