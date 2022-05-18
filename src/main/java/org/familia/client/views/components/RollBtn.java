package org.familia.client.views.components;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RollBtn extends JButton {
    private Image rollBtn;

    public RollBtn(int x, int y, int size) {
        setBounds(x, y, size, size);
        setContentAreaFilled(false);
        setBorderPainted(false);

        try {
            rollBtn = Asset.getImage("RollBtn.png")
                    .getScaledInstance(size, size, Image.SCALE_DEFAULT);
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(rollBtn, 0, 0, null);
    }
}
