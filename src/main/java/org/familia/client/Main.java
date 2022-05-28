package org.familia.client;

import org.familia.client.views.frames.MainFrame;
import org.familia.client.views.layouts.implementations.InGameLayout;
import org.familia.client.views.layouts.implementations.StartGameLayout;

import javax.swing.*;

/**
 * Create frame and its corresponding layout file.
 */
public class Main {
    public static final int WIDTH = 960;
    public static final int HEIGHT = 720;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                InGameLayout inGameLayout = new InGameLayout();
                StartGameLayout startGameLayout = new StartGameLayout();
                new MainFrame("Snake And Ladder", inGameLayout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
