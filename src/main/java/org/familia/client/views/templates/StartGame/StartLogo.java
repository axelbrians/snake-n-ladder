package org.familia.client.views.templates.StartGame;

import org.familia.client.controllers.StartGame.StartLogoController;
import org.familia.client.views.components.GameLogo;
import org.familia.client.views.components.GroupButton;

import javax.swing.*;
import java.awt.*;

public class StartLogo extends JLayeredPane {
    private final StartLogoController startLogoController;

    private GameLogo gameLogo;
    private GroupButton groupButton;

    public StartLogo(int x, int y, int width, int height) throws Exception {
        setLayout(null);
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        startLogoController = new StartLogoController(this);
        gameLogo = new GameLogo(0, 0, 454, 261);
        groupButton = new GroupButton(9, 322, 436, 52);

        add(gameLogo, 0, 0);
        add(groupButton, 0, 0);

        addListener();
    }

    private void addListener() {
        JButton startButton = groupButton.getStartBtn(),
                creditButton = groupButton.getCreditBtn(),
                exitButton = groupButton.getExitBtn();

        startButton.setActionCommand(StartLogoController.START_BUTTON_ACTION_COMMAND);
        startButton.addActionListener(startLogoController);

        creditButton.setActionCommand(StartLogoController.CREDIT_BUTTON_ACTION_COMMAND);
        creditButton.addActionListener(startLogoController);

        exitButton.setActionCommand(StartLogoController.EXIT_BUTTON_ACTION_COMMAND);
        exitButton.addActionListener(startLogoController);
    }
}
