package org.familia.client.controllers;

import org.familia.client.views.components.overlay.WinOverlay;
import org.familia.client.views.layouts.Layout;
import org.familia.client.views.layouts.implementations.InGameLayout;

public class InGameController extends Controller {
    protected InGameLayout layout;
    private String[] players = { "Player1", "Player2", "ads", "sdfs" };
    private int currPlayerIdx = 0;

    public InGameController() throws Exception {
        layout = new InGameLayout(players, currPlayerIdx);
    }

    public Layout getLayout() {
        return layout;
    }
}
