package org.familia.server

import kotlinx.coroutines.*
import org.familia.client.common.request.Request
import org.familia.client.common.request.match.Match
import org.familia.client.common.request.match.MatchType
import org.familia.client.common.request.user.User
import org.familia.client.common.response.Response
import org.familia.client.common.response.user.UserResponse
import org.familia.client.common.status.Status
import java.io.InputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import kotlin.system.exitProcess

fun main() {
    val socket = Socket("localhost", 6969)
    val coroutineScope = CoroutineScope(Dispatchers.IO)

    println("= = = Connected to localhost:6969 = = =")

    val objectOutput = ObjectOutputStream(socket.getOutputStream())
    val objectInput = ObjectInputStream(socket.getInputStream())

    var user:User

    while (true) {
        println("Enter your username (3 to 8 characters):")

        var username: String

        while (true) {
            username = readln()

            if (username.length in 3..8) {
                break
            } else {
                println("Username must be in 3 up to 8 characters")
            }
        }
        user = User(username)
        objectOutput.writeObject(Request(user))


        val response = objectInput.readObject() as Response
        val data = response.data as UserResponse


        println("message: ${response.message}")

        if (response.status == Status.Success) {
            println("username: ${data.username}")
            break
        }
    }

    val readJob = handleReadMessage(coroutineScope, objectInput)

    while (true) {
        val matching = readln()
        if (matching == "/match 1") {
            println("Entering match queue for 2 player")
            val match = Match(user, MatchType.TwoPlayer)
            objectOutput.writeObject(Request(match))
        } else if (matching == "/match 2") {
            println("Entering match queue for 4 player")
            val match = Match(user, MatchType.FourPlayer)
            objectOutput.writeObject(Request(match))
        } else if (matching == "/exit") {
            println("Exiting...")
            break
        }
    }

    coroutineScope.cancel()
}

private fun handleReadMessage(coroutineScope: CoroutineScope, objectInput: ObjectInputStream): Job {
    return coroutineScope.launch {
        var crashCounter = 0

        while (true) {
            if (crashCounter > 3) {
                println("= = = Connection Lost = = =")
                exitProcess(0)
            }

            try {
                val response = objectInput.readObject() as Response
                val data = response.data

                println("message: ${response.message}")
                if (data is UserResponse) {
                    println("username: ${data.username}")
                }

                println("= = = = = =")
            } catch (e: Exception) {
                crashCounter++
                println("= = = Something went wrong, message not received = = =")
                println("cause: $e")
                println("error message: ${e.message}")
                delay(1000)
            }
        }
    }
}



private fun printHelp() {
    println("= = = Snake-n-Ladder CLI Guide = = =")
    println("/b <message> - broadcast message")
    println("/s - see online users")
    println("/w <username> /m <message> - send message to user")
    println("/h - print help")
    println("/exit - exit")
    println("= = = = = =")
}