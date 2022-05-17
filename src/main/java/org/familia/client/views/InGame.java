package org.familia.client.views;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class InGame extends JPanel {

    private Image background;
    private final int areaWidth;
    private final int areaHeight;

    public InGame(int width, int height) {
        this.areaWidth = width;
        this.areaHeight = height;
        this.setPreferredSize(new Dimension(areaWidth, areaHeight));
        setLayout(null);
        try {
            background = Asset.getImage("GrassBg.jpg")
                    .getScaledInstance(areaWidth, areaHeight, Image.SCALE_DEFAULT);
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
