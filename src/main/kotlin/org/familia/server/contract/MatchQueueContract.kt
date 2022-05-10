package org.familia.server.contract

interface MatchQueueContract {
    fun onMatchRequested(username: String, room: Int)
}