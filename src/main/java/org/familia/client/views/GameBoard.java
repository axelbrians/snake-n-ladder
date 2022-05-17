package org.familia.client.views;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameBoard extends JLayeredPane {
    private Image background;
    public final int width = 617;
    public final int height = 617;

    public GameBoard() {
        setBounds(30, 52, width, height);
        setPreferredSize(new Dimension(width, height));

        try {
            background = Asset.getImage("GameBoard.png")
                    .getScaledInstance(width, height, Image.SCALE_DEFAULT);
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
