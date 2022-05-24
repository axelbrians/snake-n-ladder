package org.familia.client.views.layouts;

import org.familia.client.Main;
import org.familia.client.views.templates.ChatBox;
import org.familia.client.views.templates.GameBoard;
import org.familia.client.views.templates.PlayerBox;
import org.familia.client.views.templates.RollBox;
import org.familia.client.views.components.Background;
import org.familia.client.views.components.overlay.DiceOverlay;
import org.familia.client.views.components.overlay.LoadingOverlay;
import org.familia.client.views.components.overlay.NetworkErrorOverlay;
import org.familia.client.views.components.overlay.Overlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * Adjust frame size and contents.
 */
public class InGameLayout extends JLayeredPane {
    public HashMap<String, Overlay> overlays = new HashMap<>();
    private Background background;
    private GameBoard board;
    private PlayerBox playerBox;
    private RollBox rollBox;
    private ChatBox chatBox;

    public InGameLayout() throws Exception {
        setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));

        String[] players = { "Player1", "Player2" };
        overlays.put("dice", new DiceOverlay());
        overlays.put("loading", new LoadingOverlay());
        overlays.put("networkError", new NetworkErrorOverlay());

        background = new Background(Main.WIDTH, Main.HEIGHT, "GrassBg.jpg");
        board = new GameBoard(30, 52, 617);
        playerBox = new PlayerBox(664, 52, 277, 131, players);
        rollBox = new RollBox(664, 218, 277, 230);
        chatBox = new ChatBox(664, 479, 277, 190);

        addOverlayListener();

        add(background, 0, 0);
        add(board, 1, 1);
        add(rollBox, 1, 1);
        add(playerBox, 1, 1);
        add(chatBox, 1, 1);
//        addOverlay("networkError");
    }

    /**
     * Add overlay with animation.
     *
     * @param name
     */
    public void addOverlay(String name) {
        if (!overlays.containsKey(name)) {
            throw new IllegalArgumentException("No " + name + " found in overlays.");
        }
        Overlay overlay = overlays.get(name);
        overlay.addToPane(this, 2, 2);

        chatBox.disableChatbox();
        rollBox.disableRollBox();
    }

    /**
     * Add overlay listener to remove overlay with animation.
     */
    private void addOverlayListener() {
        JLayeredPane pane = this;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                overlays.get("dice").removeFromPane(pane);
                overlays.get("loading").removeFromPane(pane);

                chatBox.enableChatbox();
                rollBox.enableRollBox();

                repaint();
            }
        });
    }
}
