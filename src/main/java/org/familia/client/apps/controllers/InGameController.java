package org.familia.client.apps.controllers;

import org.familia.client.views.layouts.Layout;
import org.familia.client.views.layouts.implementations.InGameLayout;

public class InGameController extends Controller {
    protected InGameLayout layout;
    private String[] players = { "Player1", "Player2", "ads", "sdfs" };
    private int currPlayerIdx = 0;

    public InGameController() throws Exception {
        layout = new InGameLayout(players, currPlayerIdx);
//        layout.playerWin(1);
//        layout.playerBox.next();
//        layout.addOverlayToPane("networkError");
    }

    public Layout getLayout() {
        return layout;
    }
}
