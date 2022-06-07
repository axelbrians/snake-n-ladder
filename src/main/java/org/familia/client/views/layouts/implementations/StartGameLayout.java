package org.familia.client.views.layouts.implementations;

import org.familia.client.Main;
import org.familia.client.views.components.Background;
import org.familia.client.views.layouts.Layout;
import org.familia.client.views.templates.StartGame.*;

public class StartGameLayout extends Layout {
    private final StartLogo startLogo;
    private final CreditTemplate creditTemplate;
    private final EnterUsername enterUsername;
    private final SelectPlayer selectPlayer;
    private final NetworkError networkError;

    public StartGameLayout() throws Exception {
        super();

        background = new Background(Main.WIDTH, Main.HEIGHT, "ForestBg.png");
        startLogo = new StartLogo(253, 206, 454, 374);
        creditTemplate = new CreditTemplate(257, 111, 445, 517);
        enterUsername = new EnterUsername(257, 243, 445, 235);
        selectPlayer = new SelectPlayer(257, 243, 445, 235);
        networkError = new NetworkError(257, 243, 445, 235);

        startLogo.setVisible(true);
        creditTemplate.setVisible(false);
        enterUsername.setVisible(false);
        selectPlayer.setVisible(false);
        networkError.setVisible(false);

        add(background, 0, 0);
        add(startLogo, 1, 1);
        add(creditTemplate, 1, 1);
        add(enterUsername, 1, 1);
        add(selectPlayer, 1, 1);
        add(networkError, 1, 1);
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

    public SelectPlayer getSelectPlayer() {
        return selectPlayer;
    }

    public NetworkError getNetworkError() {
        return networkError;
    }

    public void generateAllVisibleFalse() {
        startLogo.setVisible(false);
        creditTemplate.setVisible(false);
        enterUsername.setVisible(false);
        selectPlayer.setVisible(false);
        networkError.setVisible(false);
    }
}
