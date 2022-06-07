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

//        new Thread(() -> {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            layout.board.movePlayerTo(1, 2);
//            try {
//                layout.playerBox.next();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();

//        layout.playerWin(0);
//        layout.playerWin(1);
//        layout.playerBox.next();

//        layout.addOverlayToPane("dice");

//        layout.addOverlayToPane("loading");

//        layout.addOverlayToPane("networkError");

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            layout.removeOverlayFromPane();
        }).start();
    }

    public Layout getLayout() {
        return layout;
    }
}
