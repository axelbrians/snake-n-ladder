package org.familia.client.views.components;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Background extends JPanel {
    private final Image background;

    public Background(int width, int height, String imageName) throws IOException {
        setBounds(0, 0, width, height);

        background = Asset.getImage(imageName, width, height);
    }

    public Background(int x, int y, int width, int height, String imageName) throws IOException {
        setBounds(x, y, width, height);

        background = Asset.getImage(imageName, width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
