package org.familia.client.views.templates.StartGame;

import org.familia.client.controller.StartGame.CreditTemplateController;
import org.familia.client.views.components.Background;
import org.familia.client.views.components.button.DefaultWoodButton;

import javax.swing.*;
import java.awt.*;

public class CreditTemplate extends JLayeredPane {
    private final CreditTemplateController creditTemplateController;

    private final Background background;
    private final DefaultWoodButton backButton;

    public CreditTemplate(int x, int y, int width, int height) throws Exception {
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        creditTemplateController = new CreditTemplateController(this);
        background = new Background(445, 517, "CreditBoard.png");
        backButton = new DefaultWoodButton(169, 430, "Back");

        add(background, 0, 0);
        add(backButton, 1, 1);

        addListener();
    }

    private void addListener() {
        backButton.setActionCommand(CreditTemplateController.BACK_BUTTON_ACTION_COMMAND);
        backButton.addActionListener(creditTemplateController);
    }
}
