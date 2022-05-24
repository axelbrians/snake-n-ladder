package org.familia.client.views.templates;

import org.familia.client.views.components.Background;
import org.familia.client.views.components.button.DefaultWoodButton;
import org.familia.client.views.layouts.implementations.StartGameLayout;

import javax.swing.*;
import java.awt.*;

public class CreditTemplate extends JLayeredPane {
    private Background background;
    private DefaultWoodButton backButton;

    public CreditTemplate(int x, int y, int width, int height, StartGameLayout parent) throws Exception {
        Component thisComponent = this;

        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        background = new Background(445, 517, "CreditBoard.png");
        backButton = new DefaultWoodButton(169, 430, "Back");
        backButton.addActionListener(e -> {
            thisComponent.setVisible(false);
            parent.getStartLogo().setVisible(true);
        });

        add(background, 0, 0);
        add(backButton, 1, 1);
    }
}
