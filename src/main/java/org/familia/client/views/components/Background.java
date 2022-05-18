package org.familia.client.views.components;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Background extends JPanel {
    private Image background;

    public Background(int width, int height, String imageName) {
        setBounds(0, 0, width, height);

        try {
            background = Asset.getImage(imageName)
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
