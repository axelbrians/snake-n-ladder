package org.familia.client.apps.networks.response.board;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class PlayerMoveResponse implements Serializable {

    public String username;
    public Integer diceRoll;

    public PlayerMoveResponse(
        @NotNull String username,
        @NotNull Integer diceRoll
    ) {
        this.username = username;
        this.diceRoll = diceRoll;
    }
}
