package org.familia.client.views.backgrounds;

import org.familia.client.helpers.Asset;
import org.familia.client.views.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class InGameBackground extends JPanel {
    private Image background;
    private final int areaWidth;
    private final int areaHeight;

    public InGameBackground(int width, int height) {
        this.areaWidth = width;
        this.areaHeight = height;
        this.setPreferredSize(new Dimension(areaWidth, areaHeight));
        try {
            background = Asset.getImage("GrassBg.jpg")
                    .getScaledInstance(areaWidth, areaHeight, Image.SCALE_DEFAULT);
        } catch(IOException e) {
            System.out.println(e);
        }
        this.add(new GameBoard());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
