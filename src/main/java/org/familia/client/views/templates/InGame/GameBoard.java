package org.familia.client.views.templates.InGame;

import org.familia.client.helpers.Asset;
import org.familia.client.helpers.Coordinate;
import org.familia.client.views.components.Background;
import org.familia.client.views.components.PlayerPiece;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameBoard extends JLayeredPane {
    private Background background;
    private PlayerPiece[] playerPiece;
    private final Coordinate startCoordinate; // Start coordinate for player piece

    public GameBoard(int x, int y, int size, int playerCount) throws IOException {
        setBounds(x, y, size, size);
        setPreferredSize(new Dimension(size, size));

        background = new Background(size, size, "GameBoard.png");
        add(background);

        playerPiece = new PlayerPiece[playerCount];
        for (int i = 0; i < playerCount; i++) {
            playerPiece[i] = new PlayerPiece(i, Asset.ICONPATH[i], 30, 550 );
            add(playerPiece[i], 1, 1);
            playerPiece[i].setVisible(true);
        }

        startCoordinate = new Coordinate(44, 573);
        for (int i = 20; i <= 40; i++) {
            startCoordinate.moveTo(i);
            System.out.printf("X: %f, Y: %f\n", startCoordinate.x, startCoordinate.y);
        }
    }
}
