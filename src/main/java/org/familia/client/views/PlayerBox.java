package org.familia.client.views;

import org.familia.client.helpers.Asset;
import org.familia.client.views.components.PlayerIcon;

import javax.swing.*;
import java.awt.*;

public class PlayerBox extends JLayeredPane {
    private final Image background;
    private final String[] iconPath = {
            "players/Blue.png",
            "players/Green.png",
            "players/Pink.png",
            "players/Yellow.png",
    };
    private final String[] players; // Contain player name sorted by turn order.
    private final int playerCount;

    private PlayerIcon[] playerIcons; // Contain player icon object sorted by turn order.
    private int currPlayerIdx = 0;

    public PlayerBox(int x, int y, int width, int height, String[] players) throws Exception {
        int playerCount = players.length;
        if (playerCount != 2 && playerCount != 4) {
            throw new Exception("Invalid player count.");
        }
        this.players = players;
        this.playerCount = playerCount;

        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        background = Asset.getImage("PlayerBox.png", width, height);

        // Init player icons
        int xPos = 19;
        playerIcons = new PlayerIcon[playerCount];
        for (int i = 0; i < playerCount; i++) {
            playerIcons[i] = new PlayerIcon(xPos, 49, iconPath[i], players[i]);
            add(playerIcons[i], 1, 1);
            xPos += 66;
        }
    }

    /**
     * Set current player equal to next player.
     */
    public void next() {
        currPlayerIdx++;
        if (currPlayerIdx >= playerCount) {
            currPlayerIdx = 0;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
