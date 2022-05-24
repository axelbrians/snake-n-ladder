package org.familia.client.views.backgrounds;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameBackground extends JPanel {
    private Image background;

    public GameBackground(String imagePath, int width, int height) {
        setBounds(0, 0, width, height);

        try {
            background = Asset.getImage(imagePath)
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
