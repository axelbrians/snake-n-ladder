package org.familia.client.views.frames;

import org.familia.client.views.components.overlay.NetworkErrorOverlay;
import org.familia.client.views.layouts.GameLayout;
import org.familia.client.views.layouts.implementations.InGameLayout;

import javax.swing.*;
import java.awt.event.*;

/**
 * Construct frame and link it with its layout file.
 */
public class MainFrame extends JFrame {
    private Timer timer;


    /**
     * @param title for the name of Frame
     * @param layout for layout that will be used
     */
    public MainFrame(String title, GameLayout layout) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        addClosePrompt(this);

        setContentPane(layout);
        if (layout.overlays.containsKey("networkError")) {
            ((NetworkErrorOverlay) layout.overlays.get("networkError")).setExitAction(this);
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addClosePrompt(JFrame frame) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            int choose = JOptionPane.showConfirmDialog(frame, "Do you really want to exit the application?",
                    "Confirm Close", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(choose == JOptionPane.YES_OPTION) {
                e.getWindow().dispose();
            } else {
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
            }
        });
    }
}
