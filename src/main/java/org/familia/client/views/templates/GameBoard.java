package org.familia.client.views.templates;

import org.familia.client.views.components.Background;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameBoard extends JLayeredPane {
    private Background background;

    public GameBoard(int x, int y, int size) throws IOException {
        setBounds(x, y, size, size);
        setPreferredSize(new Dimension(size, size));

        background = new Background(size, size, "GameBoard.png");
        add(background);
    }
}
