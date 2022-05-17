package org.familia.client.views.backgrounds;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class InGameBackground extends JPanel {
    private Image background;

    public InGameBackground(int width, int height) {
        setBounds(0, 0, width, height);

        try {
            background = Asset.getImage("GrassBg.jpg")
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
