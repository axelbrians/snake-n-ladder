package org.familia.client.views.layouts.implementations;

import org.familia.client.Main;
import org.familia.client.views.components.Background;
import org.familia.client.views.layouts.GameLayout;
import org.familia.client.views.templates.CreditTemplate;
import org.familia.client.views.templates.StartLogo;

public class StartGameLayout extends GameLayout {
    private StartLogo startLogo;
    private CreditTemplate creditTemplate;

    public StartGameLayout() throws Exception {
        super();

        background = new Background(Main.WIDTH, Main.HEIGHT, "ForestBg.png");
        startLogo = new StartLogo(253, 206, 454, 374);
        creditTemplate = new CreditTemplate(257, 111, 445, 517);

        creditTemplate.setVisible(false);

        add(background, 0, 0);
        add(startLogo, 1, 1);
        add(creditTemplate, 1, 1);
    }

    public StartLogo getStartLogo() {
        return startLogo;
    }

    public CreditTemplate getCreditTemplate() {
        return creditTemplate;
    }
}
