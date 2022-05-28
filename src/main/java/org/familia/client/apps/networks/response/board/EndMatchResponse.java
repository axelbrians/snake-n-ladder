package org.familia.client.apps.networks.response.board;

import java.io.Serializable;
import java.util.List;

public class EndMatchResponse implements Serializable {

    public List<String> playerRanking;

    public EndMatchResponse(List<String> playerRanking) {
        this.playerRanking = playerRanking;
    }
}
