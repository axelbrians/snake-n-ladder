package org.familia.client.views;

import org.familia.client.helpers.Asset;
import org.familia.client.views.components.RollBtn;
import org.familia.client.views.components.RollSlider;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RollBox extends JLayeredPane {
    private final Image background;

    public RollBox(int x, int y, int width, int height) throws IOException {
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        background = Asset.getImage("RollBox.png", width, height);

        add(new RollBtn(94, 123, 86));
        add(new RollSlider(132, 38, 6, 59));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
