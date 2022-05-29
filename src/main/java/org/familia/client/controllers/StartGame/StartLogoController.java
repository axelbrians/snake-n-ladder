package org.familia.client.controllers.StartGame;

import org.familia.client.providers.ComponentProvider;
import org.familia.client.views.frames.MainFrame;
import org.familia.client.views.layouts.implementations.StartGameLayout;
import org.familia.client.views.templates.StartGame.StartLogo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartLogoController implements ActionListener {
    public static final String START_BUTTON_ACTION_COMMAND = "gb.start";
    public static final String CREDIT_BUTTON_ACTION_COMMAND = "gb.credit";
    public static final String EXIT_BUTTON_ACTION_COMMAND = "gb.exit";

    private StartLogo startLogo;

    public StartLogoController(StartLogo startLogo) {
        this.startLogo = startLogo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonAction = e.getActionCommand();
        switch (buttonAction) {
            case START_BUTTON_ACTION_COMMAND -> startButtonAction();
            case CREDIT_BUTTON_ACTION_COMMAND -> creditButtonAction();
            case EXIT_BUTTON_ACTION_COMMAND -> exitButtonAction();
        }
    }

    private void startButtonAction() {
        StartGameLayout startGameLayout = (StartGameLayout) ComponentProvider.getGameLayoutAncestor(startLogo);
        startGameLayout.getEnterUsername().setVisible(true);
        startGameLayout.getStartLogo().setVisible(false);
    }

    private void creditButtonAction() {
        StartGameLayout startGameLayout = (StartGameLayout) ComponentProvider.getGameLayoutAncestor(startLogo);
        startGameLayout.getCreditTemplate().setVisible(true);
        startGameLayout.getStartLogo().setVisible(false);
    }

    private void exitButtonAction() {
        MainFrame topFrame = ComponentProvider.getFrameAncestor(startLogo);

        int choose = JOptionPane.showConfirmDialog(topFrame, "Do you really want to exit the application?",
                "Confirm Close", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if(choose == JOptionPane.YES_OPTION) {
            topFrame.dispose();
        }
    }
}
