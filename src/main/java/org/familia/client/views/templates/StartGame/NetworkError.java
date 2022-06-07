package org.familia.client.views.templates.StartGame;

import org.familia.client.controllers.StartGame.NetworkErrorController;
import org.familia.client.views.components.Background;
import org.familia.client.views.components.button.DefaultWoodButton;

import javax.swing.*;
import java.awt.*;

public class NetworkError extends JLayeredPane {
    private final NetworkErrorController networkErrorController;

    private final Background background;
    private final DefaultWoodButton backButton;

    public NetworkError(int x, int y, int width, int height) throws Exception {
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        networkErrorController = new NetworkErrorController(this);
        background = new Background(445, 235, "NetworkErrorBoard.png");
        backButton = new DefaultWoodButton(167, 154, "Close");

        add(background, 0, 0);
        add(backButton, 1, 1);

        addListener();
    }

    private void addListener() {
        backButton.setActionCommand(NetworkErrorController.BACK_BUTTON_ACTION_COMMAND);
        backButton.addActionListener(networkErrorController);
    }
}
