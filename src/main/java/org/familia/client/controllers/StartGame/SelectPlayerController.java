package org.familia.client.controllers.StartGame;

import org.familia.client.Main;
import org.familia.client.providers.ComponentProvider;
import org.familia.client.views.frames.MainFrame;
import org.familia.client.views.layouts.implementations.StartGameLayout;
import org.familia.client.views.templates.StartGame.SelectPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectPlayerController implements ActionListener {
    public static final String PLAYER_2_BUTTON_ACTION_COMMAND = "p2.select";
    public static final String PLAYER_4_BUTTON_ACTION_COMMAND = "p4.select";
    private SelectPlayer selectPlayer;

    public SelectPlayerController(SelectPlayer selectPlayer) {
        this.selectPlayer = selectPlayer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonAction = e.getActionCommand();
        switch (buttonAction) {
            case PLAYER_2_BUTTON_ACTION_COMMAND -> player2ButtonAction();
            case PLAYER_4_BUTTON_ACTION_COMMAND -> player4ButtonAction();
        }
    }

    private void player2ButtonAction() {
        int playerCount = 2;
        getToInGame(playerCount);
    }

    private void player4ButtonAction() {
        int playerCount = 4;
        getToInGame(playerCount);
    }

    private void getToInGame(int countPlayer) {
        StartGameLayout startGameLayout = (StartGameLayout) ComponentProvider.getGameLayoutAncestor(selectPlayer);
        selectPlayer.setVisible(false);
        startGameLayout.getStartLogo().setVisible(true);

        // Redirect to in game view.
        MainFrame frame = ComponentProvider.getFrameAncestor(startGameLayout);
        try {
            frame.setController(Main.inGameController);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
