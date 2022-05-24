package org.familia.client.views.templates;

import org.familia.client.views.components.GameLogo;
import org.familia.client.views.components.GroupButton;
import org.familia.client.views.layouts.implementations.StartGameLayout;

import javax.swing.*;
import java.awt.*;

public class StartLogo extends JLayeredPane {
    private final StartGameLayout startGameLayout;
    private GameLogo gameLogo;
    private GroupButton groupButton;

    public StartLogo(int x, int y, int width, int height, StartGameLayout parent) throws Exception {
        setLayout(null);
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        this.startGameLayout = parent;
        gameLogo = new GameLogo(0, 0, 454, 261);
        groupButton = new GroupButton(9, 322, 436, 52, this);

        add(gameLogo, 0, 0);
        add(groupButton, 0, 0);
    }

    public StartGameLayout getStartGameLayout() {
        return startGameLayout;
    }
}
