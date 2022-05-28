package org.familia.server

import org.familia.server.core.SnakeServer

const val port = 6900

fun main() {

    with(SnakeServer()) {
        serve(port)
    }
}