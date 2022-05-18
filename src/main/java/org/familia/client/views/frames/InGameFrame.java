package org.familia.client.views.frames;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Construct frame and link it with its layout file.
 */
public class InGameFrame extends JFrame {
    private final JLayeredPane layout;

    /**
     * Construct Frame
     *
     * @param title
     */
    public InGameFrame(String title, JLayeredPane layout) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        addClosePrompt(this);

        this.layout = layout;
        setContentPane(layout);

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
