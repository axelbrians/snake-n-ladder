package org.familia.client.views.layouts.implementations;

import org.familia.client.Main;
import org.familia.client.views.components.Background;
import org.familia.client.views.layouts.Layout;
import org.familia.client.views.templates.CreditTemplate;
import org.familia.client.views.templates.StartLogo;

public class StartGameLayout extends Layout {
    private StartLogo startLogo;
    private CreditTemplate creditTemplate;

    public StartGameLayout() throws Exception {
        super();

        background = new Background(Main.WIDTH, Main.HEIGHT, "ForestBg.png");
        startLogo = new StartLogo(253, 206, 454, 374, this);
        creditTemplate = new CreditTemplate(257, 111, 445, 517, this);

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
