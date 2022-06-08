package org.familia.client.views.frames;

import org.familia.client.controllers.Controller;
import org.familia.client.networks.SocketConnection;
import org.familia.client.providers.DataProvider;
import org.familia.client.views.components.overlay.ClosableFrame;
import org.familia.client.views.layouts.HasOverlay;
import org.familia.client.views.components.overlay.Overlay;
import org.familia.client.views.layouts.Layout;
import org.familia.client.views.layouts.implementations.StartGameLayout;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Construct frame and link it with its layout file.
 */
public class MainFrame extends JFrame {
    private Timer timer;
    private Controller controller;
    private Layout layout;
    public DataProvider dataProvider;
    public SocketConnection socketConnection;

    /**
     * @param title for the name of Frame
     * @param controller for layout that will be used
     */
    public MainFrame(String title, Controller controller) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        addClosePrompt(this);

        setController(controller);
        setProvider();
        checkAndSetConnection();

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

    public void setController(Controller controller) {
        this.controller = controller;
        this.layout = controller.getLayout();

        if (getContentPane() != null) {
            getContentPane().removeAll();
        }
        setContentPane(layout);
        if (layout instanceof HasOverlay) {
            setCloseFrameAction((HasOverlay) layout);
        }
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void setCloseFrameAction(HasOverlay layout) {
        for (Overlay overlay: layout.overlays.values()) {
            if (!(overlay instanceof ClosableFrame)) {
                continue;
            }
            ((ClosableFrame) overlay).setCloseFrameAction(this);
        }
    }

    public void setProvider() {
        dataProvider = new DataProvider();
    }

    public void checkAndSetConnection() {
        try {
            if (socketConnection != null) {
                return;
            }
            socketConnection = new SocketConnection(dataProvider);
        } catch (Exception exception) {
            Layout layout = controller.getLayout();
            if (layout.getClass() == StartGameLayout.class) {
                StartGameLayout startGameLayout = (StartGameLayout) layout;
                startGameLayout.generateAllVisibleFalse();
                startGameLayout.getNetworkError().setVisible(true);
            }
            exception.printStackTrace();
        }
    }
}
