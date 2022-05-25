package org.familia.client.views.components;

import org.familia.client.views.components.button.DefaultWoodButton;
import org.familia.client.views.layouts.implementations.StartGameLayout;
import org.familia.client.views.templates.StartLogo;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GroupButton extends JLayeredPane {
    private final StartLogo startLogo;
    public DefaultWoodButton startBtn, creditBtn, exitBtn;
    public static String START = "START", CREDIT = "CREDIT", EXIT = "EXIT";

    public GroupButton(int x, int y, int width, int height, StartLogo parent) throws IOException {
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));
        setOpaque(false);

        this.startLogo = parent;
        startBtn = new DefaultWoodButton(0, 0, START);
        creditBtn = new DefaultWoodButton(162, 0, CREDIT);
        exitBtn = new DefaultWoodButton(324, 0, EXIT);

        creditBtn.addActionListener(e -> {
            StartGameLayout startGameLayout = getStartLogo().getStartGameLayout();
            startGameLayout.getCreditTemplate().setVisible(true);
            startGameLayout.getStartLogo().setVisible(false);
        });

        exitBtn.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose();
        });

        add(startBtn, 0, 0);
        add(creditBtn, 0, 0);
        add(exitBtn, 0, 0);
    }

    public StartLogo getStartLogo() {
        return startLogo;
    }
}
