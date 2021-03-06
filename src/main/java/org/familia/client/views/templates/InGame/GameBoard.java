package org.familia.client.views.templates.InGame;

import org.familia.client.helpers.Asset;
import org.familia.client.views.components.Background;
import org.familia.client.views.components.PlayerPiece;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameBoard extends JLayeredPane {
    private Background background;
    private PlayerPiece[] playerPiece;

    public GameBoard(int x, int y, int size, int playerCount) throws IOException {
        setBounds(x, y, size, size);
        setPreferredSize(new Dimension(size, size));

        background = new Background(size, size, "GameBoard.png");
        add(background);

        playerPiece = new PlayerPiece[playerCount];
        for (int i = 0; i < playerCount; i++) {
            playerPiece[i] = new PlayerPiece(i, Asset.ICONPATH[i], 30, 550, size);
            add(playerPiece[i], 1, 1);
        }
    }

    /**
     * Move a player to a tile.
     *
     * @param playerIdx
     * @param tile
     */
    public void movePlayerTo(int playerIdx, int tile) {
        int currTile = playerPiece[playerIdx].getTileNumber();
        while (currTile < tile) {
            currTile++;
            playerPiece[playerIdx].moveTo(currTile);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
