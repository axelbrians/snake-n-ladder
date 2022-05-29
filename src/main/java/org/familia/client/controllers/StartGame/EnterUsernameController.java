package org.familia.client.controllers.StartGame;

import org.familia.client.controllers.InGameController;
import org.familia.client.providers.ComponentProvider;
import org.familia.client.views.components.TextField;
import org.familia.client.views.frames.MainFrame;
import org.familia.client.views.layouts.implementations.StartGameLayout;
import org.familia.client.views.templates.StartGame.EnterUsername;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterUsernameController implements ActionListener {
    public static final String SUBMIT_BUTTON_ACTION_COMMAND = "eu.submit";
    private EnterUsername enterUsername;

    public EnterUsernameController(EnterUsername enterUsername) {
        this.enterUsername = enterUsername;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonAction = e.getActionCommand();
        switch (buttonAction) {
            case SUBMIT_BUTTON_ACTION_COMMAND -> submitButtonAction();
        }
    }

    private void submitButtonAction() {
        StartGameLayout startGameLayout = (StartGameLayout) ComponentProvider.getGameLayoutAncestor(enterUsername);
        TextField textField = enterUsername.getTextField();

        String BLANK = "";
        String username = !textField.isTextPlaceholder() ? textField.getText() : BLANK;
        if (username.equals(BLANK)) {
            return;
        }
        System.out.println("Username:" + username);

        // Redirect to in game view.
        MainFrame frame = ComponentProvider.getFrameAncestor(startGameLayout);
        try {
            frame.setController(new InGameController());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
