package org.familia.client.common.response.board;

import java.io.Serializable;
import java.util.List;

public class BoardResponse implements Serializable {
    public List<String> players;

    public BoardResponse(List<String> players) {this.players = players;}
}
