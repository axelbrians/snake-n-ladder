package org.familia.client.views.layouts.implementations;

import org.familia.client.Main;
import org.familia.client.apps.controllers.InGameController;
import org.familia.client.providers.ComponentProvider;
import org.familia.client.views.components.Background;
import org.familia.client.views.frames.MainFrame;
import org.familia.client.views.layouts.Layout;
import org.familia.client.views.templates.StartGame.EnterUsername;
import org.familia.client.views.templates.StartGame.StartLogo;
import org.familia.client.views.templates.StartGame.CreditTemplate;

public class StartGameLayout extends Layout {
    private StartLogo startLogo;
    private CreditTemplate creditTemplate;
    private EnterUsername enterUsername;

    public StartGameLayout() throws Exception {
        super();

        background = new Background(Main.WIDTH, Main.HEIGHT, "ForestBg.png");
        startLogo = new StartLogo(253, 206, 454, 374);
        creditTemplate = new CreditTemplate(257, 111, 445, 517);
        enterUsername = new EnterUsername(257, 243, 445, 235);

        startLogo.setVisible(true);
        creditTemplate.setVisible(false);
        enterUsername.setVisible(false);

        add(background, 0, 0);
        add(startLogo, 1, 1);
        add(creditTemplate, 1, 1);
        add(enterUsername, 1, 1);
    }

    public StartLogo getStartLogo() {
        return startLogo;
    }

    public CreditTemplate getCreditTemplate() {
        return creditTemplate;
    }

    public EnterUsername getEnterUsername() {
        return enterUsername;
    }
}
