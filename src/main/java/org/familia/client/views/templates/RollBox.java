package org.familia.client.views.templates;

import org.familia.client.views.components.Background;
import org.familia.client.views.components.button.RollButton;
import org.familia.client.views.components.RollSlider;

import javax.swing.*;
import java.awt.*;
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
        rollSlider = new RollSlider(132, 38, 6, 59);

        add(background, 0, 0);
        add(rollButton, 1, 1);
        add(rollSlider, 1, 1);
    }

    public void disableRollBox() {
        rollButton.setIsActive(false);
    }

    public void enableRollBox() {
        rollButton.setIsActive(true);
    }
}
