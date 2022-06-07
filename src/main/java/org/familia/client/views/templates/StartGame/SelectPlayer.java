package org.familia.client.views.templates.StartGame;

import org.familia.client.controllers.StartGame.EnterUsernameController;
import org.familia.client.controllers.StartGame.SelectPlayerController;
import org.familia.client.views.components.Background;
import org.familia.client.views.components.button.ImageRoundButton;

import javax.swing.*;
import java.awt.*;

public class SelectPlayer extends JLayeredPane {
    private final SelectPlayerController selectPlayerController;

    private final Background background;
    private final ImageRoundButton selectPlayer2Button;
    private final ImageRoundButton selectPlayer4Button;

    public SelectPlayer(int x, int y, int width, int height) throws Exception {
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        selectPlayerController = new SelectPlayerController(this);
        background = new Background(445, 235, "SelectPlayerBoard.png");
        selectPlayer2Button = new ImageRoundButton(75, 85, 114, "Player2.png");
        selectPlayer4Button = new ImageRoundButton(257, 85, 114, "Player4.png");

        add(background, 0, 0);
        add(selectPlayer2Button, 1, 1);
        add(selectPlayer4Button, 1, 1);

        addListener();
    }

    private void addListener() {
        selectPlayer2Button.setActionCommand(SelectPlayerController.PLAYER_2_BUTTON_ACTION_COMMAND);
        selectPlayer2Button.addActionListener(selectPlayerController);

        selectPlayer4Button.setActionCommand(SelectPlayerController.PLAYER_4_BUTTON_ACTION_COMMAND);
        selectPlayer4Button.addActionListener(selectPlayerController);
    }
}
