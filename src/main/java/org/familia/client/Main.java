package org.familia.client;

import org.familia.client.views.layouts.InGameLayout;

import javax.swing.*;

public class Main {
    public static final int WIDTH = 960;
    public static final int HEIGHT = 720;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InGameLayout("Snake And Ladder");
        });
    }
}
