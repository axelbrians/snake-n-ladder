package org.familia.client.views.templates;

import org.familia.client.views.components.GameLogo;
import org.familia.client.views.components.GroupButton;

import javax.swing.*;
import java.awt.*;

public class StartLogo extends JLayeredPane {
    private GameLogo gameLogo;
    private GroupButton groupButton;

    public StartLogo(int x, int y, int width, int height) throws Exception {
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        gameLogo = new GameLogo(0, 0, 454, 261);
        groupButton = new GroupButton(9, 300, 436, 56);

        add(gameLogo, 0, 0);
        add(groupButton, 0, 0);
    }
}
