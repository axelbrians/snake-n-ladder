package org.familia.server.contract

import org.familia.client.common.request.match.MatchType

interface MatchQueueContract {
    fun onMatchRequested(username: String, matchType: MatchType)
}