package org.familia.client.views;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameBoard extends JLayeredPane {
    private Image background;
    private final int size = 617;

    public GameBoard() throws IOException {
        setBounds(30, 52, size, size);
        setPreferredSize(new Dimension(size, size));

        background = Asset.getImage("GameBoard.png", size, size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
