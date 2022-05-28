package org.familia.client.views.layouts.implementations;

import org.familia.client.Main;
import org.familia.client.views.components.overlay.*;
import org.familia.client.views.layouts.HasOverlay;
import org.familia.client.views.layouts.Layout;
import org.familia.client.views.templates.ChatBox;
import org.familia.client.views.templates.GameBoard;
import org.familia.client.views.templates.PlayerBox;
import org.familia.client.views.templates.RollBox;
import org.familia.client.views.components.Background;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * Adjust frame size and contents.
 */
public class InGameLayout extends Layout implements HasOverlay {
    private GameBoard board;
    private PlayerBox playerBox;
    private RollBox rollBox;
    private ChatBox chatBox;
    private String activeOverlay;

    private String[] players = { "Player1", "Player2" };

    public InGameLayout() throws Exception {
        super();
        this.activeOverlay = "";

        overlays.put("dice", new DiceOverlay());
        overlays.put("loading", new LoadingOverlay());
        overlays.put("networkError", new NetworkErrorOverlay());
        overlays.put("win", new WinOverlay());

        background = new Background(Main.WIDTH, Main.HEIGHT, "GrassBg.jpg");
        board = new GameBoard(30, 52, 617);
        playerBox = new PlayerBox(664, 52, 277, 131, players);
        rollBox = new RollBox(664, 218, 277, 230);
        chatBox = new ChatBox(664, 479, 277, 190);

        removeOverlayListener();

        add(background, 0, 0);
        add(board, 1, 1);
        add(rollBox, 1, 1);
        add(playerBox, 1, 1);
        add(chatBox, 1, 1);
//        addOverlayToPane("networkError");
        addWinOverlay(players[0]);
    }

    /**
     * Add overlay with animation.
     *
     * @param name
     */
    public void addOverlayToPane(String name) {
        if (!Objects.equals(name, "win")) {
            throw new IllegalArgumentException("Win overlay cannot be added from this method. Use addWindOverlay method.");
        }

        Overlay overlay = getOverlay(name, activeOverlay);
        overlay.addToPane(this, 2, 2);
        activeOverlay = name;

        chatBox.disableChatbox();
        rollBox.disableRollBox();
    }

    /**
     * Add overlay with animation.
     *
     * @param playerName
     */
    public void addWinOverlay(String playerName) {
        String name = "win";
        WinOverlay overlay = (WinOverlay) getOverlay(name, activeOverlay);
        overlay.setWinner(playerName);
        overlay.addToPane(this, 2, 2);
        activeOverlay = name;

        chatBox.disableChatbox();
        rollBox.disableRollBox();
    }

    /**
     * Add overlay listener to remove overlay with animation.
     */
    public void removeOverlayListener() {
        JLayeredPane pane = this;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (Objects.equals(activeOverlay, "") || Objects.equals(activeOverlay, "networkError")) {
                return;
            }
            overlays.get(activeOverlay).removeFromPane(pane);

            chatBox.enableChatbox();
            rollBox.enableRollBox();

            repaint();
            }
        });
    }
}
