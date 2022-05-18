package org.familia.client.views.layouts;

import org.familia.client.Main;
import org.familia.client.views.GameBoard;
import org.familia.client.views.RollBox;
import org.familia.client.views.components.Background;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Adjust frame size and contents.
 */
public class InGameLayout extends JLayeredPane {
    public InGameLayout() throws IOException {
        setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));

        JPanel background = new Background(Main.WIDTH, Main.HEIGHT, "GrassBg.jpg");
        JLayeredPane board = new GameBoard();
        JLayeredPane rollBox = new RollBox();

        add(background, 0, 0);
        add(board, 1, 1);
        add(rollBox, 1, 1);
    }
}
