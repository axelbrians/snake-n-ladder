package org.familia.client.views.components;

import org.familia.client.providers.ComponentProvider;
import org.familia.client.views.components.button.DefaultWoodButton;
import org.familia.client.views.layouts.implementations.StartGameLayout;
import org.familia.client.views.templates.StartLogo;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GroupButton extends JLayeredPane {
    public DefaultWoodButton startBtn, creditBtn, exitBtn;
    public static String START = "START", CREDIT = "CREDIT", EXIT = "EXIT";

    public GroupButton(int x, int y, int width, int height) throws IOException {
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));
        setOpaque(false);

        startBtn = new DefaultWoodButton(0, 0, START);
        creditBtn = new DefaultWoodButton(162, 0, CREDIT);
        exitBtn = new DefaultWoodButton(324, 0, EXIT);

        add(startBtn, 0, 0);
        add(creditBtn, 0, 0);
        add(exitBtn, 0, 0);
    }

    public DefaultWoodButton getCreditBtn() {
        return creditBtn;
    }

    public DefaultWoodButton getExitBtn() {
        return exitBtn;
    }
}
