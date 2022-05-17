package org.familia.client.views.layouts;

import org.familia.client.Main;
import org.familia.client.views.GameBoard;
import org.familia.client.views.backgrounds.InGameBackground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InGameLayout extends JFrame {
    private JLayeredPane lpane = new JLayeredPane();
    private final int width;
    private final int height;

    /**
     * Construct Frame
     *
     * @param title
     */
    public InGameLayout(String title) {
        this.width = Main.WIDTH;
        this.height = Main.HEIGHT;

        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setSize(width + 16, height + 39);
        setLocationRelativeTo(null);

        addClosePrompt(this);
        add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, width, height);

        setPanels();
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

    /**
     * Set all panels in the frame.
     */
    private void setPanels() {
        JPanel mainPanel = new InGameBackground(width, height);
        JLayeredPane board = new GameBoard();

        lpane.add(mainPanel, 0, 0);
        lpane.add(board, 1, 1);
    }
}
