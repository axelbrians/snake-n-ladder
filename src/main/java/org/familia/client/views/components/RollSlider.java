package org.familia.client.views.components;

import javax.swing.*;
import java.awt.*;

public class RollSlider extends JPanel {
    private final int width;
    private final int height;

    public RollSlider(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;

        setBounds(x, y, width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.decode("#737373"));
        g.fillRect(0, 0, this.width, this.height);
    }
}
