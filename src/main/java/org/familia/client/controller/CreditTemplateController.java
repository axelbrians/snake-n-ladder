package org.familia.client.controller;

import org.familia.client.providers.ComponentProvider;
import org.familia.client.views.layouts.implementations.StartGameLayout;
import org.familia.client.views.templates.CreditTemplate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditTemplateController implements ActionListener {
    public static final String BACK_BUTTON_ACTION_COMMAND = "ct.back";

    private CreditTemplate creditTemplate;

    public CreditTemplateController(CreditTemplate creditTemplate) {
        this.creditTemplate = creditTemplate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonAction = e.getActionCommand();
        switch (buttonAction) {
            case BACK_BUTTON_ACTION_COMMAND -> backButtonAction();
        }
    }

    private void backButtonAction() {
        creditTemplate.setVisible(false);

        StartGameLayout gameLayoutAncestor = (StartGameLayout) ComponentProvider.getGameLayoutAncestor(creditTemplate);
        gameLayoutAncestor.getStartLogo().setVisible(true);
    }
}
