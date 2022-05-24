package org.familia.client.views.layouts.implementations;

import org.familia.client.Main;
import org.familia.client.views.components.Background;
import org.familia.client.views.layouts.GameLayout;
import org.familia.client.views.components.GameLogo;
import org.familia.client.views.templates.StartLogo;

public class StartGameLayout extends GameLayout {
    private StartLogo startLogo;

    public StartGameLayout() throws Exception {
        super();

        background = new Background(Main.WIDTH, Main.HEIGHT, "ForestBg.png");
        startLogo = new StartLogo(240, 220, 454, 374);

        add(background, 0, 0);
        add(startLogo, 1, 1);
    }
}
