package org.familia.client;

import org.familia.client.apps.controllers.InGameController;
import org.familia.client.apps.controllers.StartGameController;
import org.familia.client.views.frames.MainFrame;

import javax.swing.*;

/**
 * Create frame and bind it to a controller.
 */
public class Main {
    public static final int WIDTH = 960;
    public static final int HEIGHT = 720;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                InGameController inGameController = new InGameController();
                StartGameController startGameController = new StartGameController();

                new MainFrame("Snake And Ladder", inGameController);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
