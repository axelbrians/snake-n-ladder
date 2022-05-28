package org.familia.client.apps.networks.request.board;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class RollDiceRequest implements Serializable {

    public Integer hitArea;
    public String username;

    public RollDiceRequest(@NotNull Integer hitArea, @NotNull String username) {
        this.hitArea = hitArea;
        this.username = username;
    }
}
