package org.familia.client.views;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameBoard extends JLayeredPane {
    private final Image background;

    public GameBoard(int x, int y, int size) throws IOException {
        setBounds(x, y, size, size);
        setPreferredSize(new Dimension(size, size));

        background = Asset.getImage("GameBoard.png", size, size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
