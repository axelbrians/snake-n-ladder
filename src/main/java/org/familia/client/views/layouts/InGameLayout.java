package org.familia.client.views.layouts;

import org.familia.client.Main;
import org.familia.client.views.ChatBox;
import org.familia.client.views.GameBoard;
import org.familia.client.views.PlayerBox;
import org.familia.client.views.RollBox;
import org.familia.client.views.components.Background;
import org.familia.client.views.components.overlay.DiceOverlay;
import org.familia.client.views.components.overlay.LoadingOverlay;
import org.familia.client.views.components.overlay.NetworkErrorOverlay;
import org.familia.client.views.components.overlay.Overlay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Adjust frame size and contents.
 */
public class InGameLayout extends JLayeredPane {
    public HashMap<String, Overlay> overlays = new HashMap<>();

    public InGameLayout() throws Exception {
        setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));

        String[] players = { "Player1", "Player2" };
        overlays.put("dice", new DiceOverlay());
        overlays.put("loading", new LoadingOverlay());
        overlays.put("networkError", new NetworkErrorOverlay());

        JPanel background = new Background(Main.WIDTH, Main.HEIGHT, "GrassBg.jpg");
        JLayeredPane board = new GameBoard(30, 52, 617);
        JLayeredPane playerBox = new PlayerBox(664, 52, 277, 131, players);
        JLayeredPane rollBox = new RollBox(664, 218, 277, 230);
        JPanel chatBox = new ChatBox(664, 479, 277, 190);

        Overlay overlay = overlays.get("dice");

        add(background, 0, 0);
        add(board, 1, 1);
        add(rollBox, 1, 1);
        add(playerBox, 1, 1);
        add(chatBox, 1, 1);

        add(overlay, 2, 2);
    }
}
