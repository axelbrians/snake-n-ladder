package org.familia.client.views;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameBoard extends JLayeredPane {
    private Image background;
    private final int size = 617;

    public GameBoard() {
        setBounds(30, 52, size, size);
        setPreferredSize(new Dimension(size, size));

        try {
            background = Asset.getImage("GameBoard.png")
                    .getScaledInstance(size, size, Image.SCALE_DEFAULT);
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
