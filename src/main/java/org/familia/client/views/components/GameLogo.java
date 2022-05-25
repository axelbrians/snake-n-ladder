package org.familia.client.views.components;

import org.familia.client.views.components.Background;

import javax.swing.*;
import java.awt.*;

public class GameLogo extends JLayeredPane {
    private Background background;

    public GameLogo(int x, int y, int width, int height) throws Exception {
        setLayout(null);
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        background = new Background(width, height, "MainLogo.png");
        add(background);
    }
}
