package org.familia.client.views;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, 50, 50);
    }
}
