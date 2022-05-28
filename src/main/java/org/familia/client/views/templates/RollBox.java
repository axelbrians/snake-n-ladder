package org.familia.client.views.templates;

import org.familia.client.views.components.Background;
import org.familia.client.views.components.button.RollButton;
import org.familia.client.views.components.RollSlider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class RollBox extends JLayeredPane {
    private final Background background;
    private final RollButton rollButton;
    private final RollSlider rollSlider;

    public RollBox(int x, int y, int width, int height) throws IOException {
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        background = new Background(width, height, "RollBox.png");
        rollButton = new RollButton(94, 123, 86);
        rollSlider = new RollSlider(47, 47, 187, 40);

        add(background, 0, 0);
        add(rollButton, 1, 1);
        add(rollSlider, 2, 2);

        setClickAction();
    }

    public void disableRollBox() {
        rollButton.setIsActive(false);
    }

    public void enableRollBox() {
        rollButton.setIsActive(true);
    }

    private void setClickAction() {
        rollButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                rollSlider.slide();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                rollSlider.slide();
                System.out.println(rollSlider.getSection());
            }
        });
    }
}
