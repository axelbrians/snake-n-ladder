package org.familia.client.views.components;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RollBtn extends JButton {
    private Image rollBtn;

    public RollBtn(int x, int y, int size) throws IOException {
        setBounds(x, y, size, size);
        setContentAreaFilled(false);
        setBorderPainted(false);

        rollBtn = Asset.getImage("RollBtn.png", size, size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(rollBtn, 0, 0, null);
    }
}
