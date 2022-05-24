package org.familia.client.views;

import org.familia.client.views.components.Background;
import org.familia.client.views.components.RollButton;
import org.familia.client.views.components.RollSlider;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RollBox extends JLayeredPane {
    private final Background background;

    public RollBox(int x, int y, int width, int height) throws IOException {
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        background = new Background(width, height, "RollBox.png");
        add(background, 0, 0);

        add(new RollButton(94, 123, 86), 1, 1);
        add(new RollSlider(132, 38, 6, 59), 1, 1);
    }
}
