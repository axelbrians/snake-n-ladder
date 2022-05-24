package org.familia.client;

import org.familia.client.views.frames.MainFrame;
import org.familia.client.views.layouts.implementations.InGameLayout;

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
                new MainFrame("Snake And Ladder", new InGameLayout());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
