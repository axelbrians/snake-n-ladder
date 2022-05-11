package org.familia.server.contract

import org.familia.client.common.request.match.MatchType
import org.familia.server.core.SnakeClient

interface MatchQueueContract {
    fun onMatchRequested(client: SnakeClient, matchType: MatchType)
}