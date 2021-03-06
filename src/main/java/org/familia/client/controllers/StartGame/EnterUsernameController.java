package org.familia.client.controllers.StartGame;

import org.familia.client.apps.networks.request.user.User;
import org.familia.client.providers.ComponentProvider;
import org.familia.client.providers.DataProvider;
import org.familia.client.providers.constants.DataProviderConstants;
import org.familia.client.views.components.TextField;
import org.familia.client.views.layouts.implementations.StartGameLayout;
import org.familia.client.views.templates.StartGame.EnterUsername;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterUsernameController implements ActionListener {
    private static final String BLANK = "";
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
        TextField textField = enterUsername.getTextField();
        String username = !textField.isTextPlaceholder() ? textField.getText() : BLANK;
        if (username.equals(BLANK)) {
            return;
        }

        // for create new value
        createNewUser(username);

        StartGameLayout startGameLayout = (StartGameLayout) ComponentProvider.getGameLayoutAncestor(enterUsername);
        enterUsername.setVisible(false);;
        startGameLayout.getSelectPlayer().setVisible(true);
    }

    private void createNewUser(String username) {
        DataProvider dataProvider = ComponentProvider.getDataProvider(enterUsername);
        User user = new User(username);
        dataProvider.addDataObject(DataProviderConstants.USER_REQUEST, user);
    }
}
