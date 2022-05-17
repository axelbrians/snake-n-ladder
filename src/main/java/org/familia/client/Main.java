package org.familia.client;

import org.familia.client.views.InGame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static final int WIDTH = 960;
    public static final int HEIGHT = 720;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake And Ladder");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int choose = JOptionPane.showConfirmDialog(frame, "Do you really want to exit the application?",
                            "Confirm Close", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if(choose == JOptionPane.YES_OPTION) {
                        e.getWindow().dispose();
                    } else {
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
                }
            });
            frame.setResizable(false);
            frame.setContentPane(new InGame(WIDTH, HEIGHT));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
