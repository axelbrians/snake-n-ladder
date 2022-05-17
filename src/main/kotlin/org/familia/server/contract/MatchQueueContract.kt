package org.familia.server.contract

import org.familia.client.apps.networks.request.match.MatchType
import org.familia.server.core.SnakeClient

interface MatchQueueContract {
    fun onMatchRequested(client: SnakeClient, matchType: MatchType)
}