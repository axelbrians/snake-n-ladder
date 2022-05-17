package org.familia.client.apps.networks.response.board;

import kotlin.Pair;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

public class BoardResponse implements Serializable {
    public List<Pair<String, Integer>> players;
    public List<Pair<Integer, Integer>> snakes;
    public List<Pair<Integer, Integer>> ladder;

    public BoardResponse(List<Pair<String, Integer>> players) {
        this.players = players;
    }
}
