package org.familia.client.views.layouts;

import org.familia.client.Main;
import org.familia.client.views.GameBoard;
import org.familia.client.views.PlayerBox;
import org.familia.client.views.RollBox;
import org.familia.client.views.components.Background;

import javax.swing.*;
import java.awt.*;

/**
 * Adjust frame size and contents.
 */
public class InGameLayout extends JLayeredPane {
    public InGameLayout() throws Exception {
        setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));

        String[] players = { "Player1", "Player2", "Player3", "Player4" };

        JPanel background = new Background(Main.WIDTH, Main.HEIGHT, "GrassBg.jpg");
        JLayeredPane board = new GameBoard(30, 52, 617);
        JLayeredPane playerBox = new PlayerBox(664, 52, 277, 131, players);
        JLayeredPane rollBox = new RollBox(664, 218, 277, 230);

        add(background, 0, 0);
        add(board, 1, 1);
        add(rollBox, 1, 1);
        add(playerBox, 1, 1);
    }
}
