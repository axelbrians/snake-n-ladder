package org.familia.client.controllers.StartGame;

import org.familia.client.providers.ComponentProvider;
import org.familia.client.views.layouts.implementations.StartGameLayout;
import org.familia.client.views.templates.StartGame.NetworkError;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NetworkErrorController implements ActionListener {
    public static final String BACK_BUTTON_ACTION_COMMAND = "ne.back";

    private NetworkError networkError;

    public NetworkErrorController(NetworkError networkError) {
        this.networkError = networkError;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonAction = e.getActionCommand();
        switch (buttonAction) {
            case BACK_BUTTON_ACTION_COMMAND -> backButtonAction();
        }
    }

    private void backButtonAction() {
        networkError.setVisible(false);

        StartGameLayout gameLayoutAncestor = (StartGameLayout) ComponentProvider.getGameLayoutAncestor(networkError);
        gameLayoutAncestor.getStartLogo().setVisible(true);
    }
}
