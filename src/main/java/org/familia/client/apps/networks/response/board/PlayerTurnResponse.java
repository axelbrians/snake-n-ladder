package org.familia.client.apps.networks.response.board;

import java.io.Serializable;

public class PlayerTurnResponse implements Serializable {

    public String username;

    public PlayerTurnResponse(String username) {
        this.username = username;
    }
}