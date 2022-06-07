package org.familia.client.views.templates.StartGame;

import org.familia.client.controllers.StartGame.EnterUsernameController;
import org.familia.client.views.components.Background;
import org.familia.client.views.components.TextField;
import org.familia.client.views.components.button.DefaultWoodButton;

import javax.swing.*;
import java.awt.*;

public class EnterUsername extends JLayeredPane {
    private final EnterUsernameController enterUsernameController;

    private final Background background;
    private final TextField textField;
    private final DefaultWoodButton submitButton;

    public EnterUsername(int x, int y, int width, int height) throws Exception {
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        enterUsernameController = new EnterUsernameController(this);
        background = new Background(445, 235, "UsernameBoard.png");
        textField = new TextField(113, 102, 221, 30, 6, "Enter username here");
        submitButton = new DefaultWoodButton(170, 160, "Submit");

        add(background, 0, 0);
        add(textField, 1, 1);
        add(submitButton, 1, 1);

        addListener();
    }

    private void addListener() {
        submitButton.setActionCommand(EnterUsernameController.SUBMIT_BUTTON_ACTION_COMMAND);
        submitButton.addActionListener(enterUsernameController);
    }

    public TextField getTextField() {
        return textField;
    }
}
