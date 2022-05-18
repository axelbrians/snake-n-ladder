package org.familia.client.views;

import org.familia.client.helpers.Asset;
import org.familia.client.views.components.RollBtn;
import org.familia.client.views.components.RollSlider;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RollBox extends JLayeredPane {
    private Image background;

    public final int width = 277;
    public final int height = 230;

    public RollBox() {
        setBounds(664, 218, width, height);
        setPreferredSize(new Dimension(width, height));

        try {
            background = Asset.getImage("RollBox.png")
                    .getScaledInstance(width, height, Image.SCALE_DEFAULT);
        } catch(IOException e) {
            System.out.println(e);
        }

        add(new RollBtn(94, 123, 86));
        add(new RollSlider(132, 38, 6, 59));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
