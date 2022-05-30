package org.familia.client.views.layouts.implementations;

import org.familia.client.Main;
import org.familia.client.views.components.overlay.*;
import org.familia.client.views.layouts.HasOverlay;
import org.familia.client.views.layouts.Layout;
import org.familia.client.views.templates.InGame.ChatBox;
import org.familia.client.views.templates.InGame.GameBoard;
import org.familia.client.views.templates.InGame.PlayerBox;
import org.familia.client.views.templates.InGame.RollBox;
import org.familia.client.views.components.Background;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Adjust frame size and contents.
 */
public class InGameLayout extends Layout implements HasOverlay {
    public GameBoard board;
    public PlayerBox playerBox;
    public RollBox rollBox;
    public ChatBox chatBox;
    public Overlay activeOverlay;

    public String[] players;
    public final int currPlayerIdx;
    public ArrayList<Integer> winnerIdx = new ArrayList<>(); // Store won player's index, sorted by win rank.

    public InGameLayout(String[] players, int currPlayerIdx) throws Exception {
        super();
        if (players.length != 2 && players.length != 4) {
            throw new Exception("Invalid player count.");
        }
        this.players = players;
        this.currPlayerIdx = currPlayerIdx;

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
    }

    /**
     * Dp stuff after a player won:
     *  - Show win overlay
     *
     * @param playerIdx
     */
    public void playerWin(int playerIdx) throws Exception {
        if (winnerIdx.contains(playerIdx)) {
            throw new IllegalArgumentException("Given player idx already won");
        }
        winnerIdx.add(playerIdx);
        playerBox.addWonPlayer(playerIdx);

        WinOverlay winOverlay = (WinOverlay) getOverlay("win");
        winOverlay.setWinner(players[playerIdx]);
        addOverlayToPane(winOverlay);

        // TODO:: Remove overlay after a delay and redirect winning player to main menu.
        removeOverlayFromPane();
    }

    /**
     * Add overlay with animation.
     *
     * @param overlay
     */
    @Override
    public void addOverlayToPane(Overlay overlay) {
        overlay.addToPane(this, 2, 2);
        activeOverlay = overlay;

        chatBox.disableChatbox();
        rollBox.disableRollBox();
    }

    /**
     * Add overlay listener to remove overlay with animation.
     */
    @Override
    public void removeOverlayFromPane() {
        if (Objects.equals(activeOverlay, null)
            || Objects.equals(activeOverlay, overlays.get("networkError"))
        ) {
            return;
        }
        activeOverlay.removeFromPane(this);
        activeOverlay = null;

        chatBox.enableChatbox();
        rollBox.enableRollBox();

        repaint();
    }

    public void removeOverlayListener()  {
        addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                removeOverlayFromPane();
            }
        });
    }
}
