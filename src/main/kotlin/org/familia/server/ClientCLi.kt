package org.familia.server

import kotlinx.coroutines.*
import org.familia.client.apps.networks.request.Request
import org.familia.client.apps.networks.request.board.RollDiceRequest
import org.familia.client.apps.networks.request.match.Match
import org.familia.client.apps.networks.request.match.MatchType
import org.familia.client.apps.networks.request.user.Message
import org.familia.client.apps.networks.request.user.User
import org.familia.client.apps.networks.response.Response
import org.familia.client.apps.networks.response.board.BoardResponse
import org.familia.client.apps.networks.response.user.UserResponse
import org.familia.client.apps.networks.status.Status
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import kotlin.system.exitProcess

fun main() {
    val socket = Socket("localhost", port)
    val coroutineScope = CoroutineScope(Dispatchers.IO)

    println("= = = Connected to localhost:$port = = =")

    val objectOutput = ObjectOutputStream(socket.getOutputStream())
    val objectInput = ObjectInputStream(socket.getInputStream())

    var user: User
    var username: String
    while (true) {
        println("Enter your username (3 to 8 characters):")



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

    handleReadMessage(coroutineScope, objectInput)

    while (true) {
        val matching = readln()
        when {
            matching.contains("/roll ") -> {
                val diceRoll = matching.substringAfter("/roll ", "1").toInt()
                println("Rolling dice...")
                objectOutput.writeObject(
                    Request(
                        RollDiceRequest(diceRoll, username)
                    )
                )
            }
            matching.contains("/message ") -> {
                val msg = matching.substringAfter("/message ")
                objectOutput.writeObject(
                    Request(
                        Message(
                            username,
                            msg
                        )
                    )
                )
            }
            matching == "/match 1" -> {
                println("Entering match queue for 2 player")
                val match = Match(
                    user,
                    MatchType.TwoPlayer
                )
                objectOutput.writeObject(Request(match))
            }
            matching == "/match 2" -> {
                println("Entering match queue for 4 player")
                val match = Match(
                    user,
                    MatchType.FourPlayer
                )
                objectOutput.writeObject(Request(match))
            }
            matching == "/exit" -> {
                println("Exiting...")
                break
            }
            else -> {
                println("Invalid command")
            }
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
                } else if (data is BoardResponse) {
                    handleMatchFlow(objectInput, data)
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

private fun handleMatchFlow(objectInput: ObjectInputStream, boardResponse: BoardResponse) {
    println("= = = = You are now in a match with ${boardResponse.players.map { it.first }} = = = =")


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