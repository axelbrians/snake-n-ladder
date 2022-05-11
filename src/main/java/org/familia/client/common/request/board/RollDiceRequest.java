package org.familia.client.common.request.board;

import java.io.Serializable;

public class RollDiceRequest implements Serializable {

    public Integer hitArea;

    public RollDiceRequest(Integer hitArea) {
        this.hitArea = hitArea;
    }
}
