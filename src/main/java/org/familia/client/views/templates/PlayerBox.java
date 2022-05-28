package org.familia.client.views.templates;

import org.familia.client.views.components.Background;
import org.familia.client.views.components.PlayerIcon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerBox extends JLayeredPane {
    private final Background background;

    private int xPos; // Current layer icon coordinate in x axis.
    private final int xAdder; // Player icon gap in x axis.

    /**
     * Player icon path.
     * Naming format:
     *  <color>.png = base image
     *  <color>White.png = image for active player
     */
    private final String[] iconPath = {
            "players/Blue",
            "players/Green",
            "players/Pink",
            "players/Yellow",
    };
    private final String[] players; // Contain player name sorted by turn order.
    private final int playerCount;

    private PlayerIcon[] playerIcons; // Contain player icon object sorted by turn order.
    private ArrayList<Integer> winnerIdx = new ArrayList<>(); // Store won player's index, sorted by win rank.
    private int currPlayerIdx = 0;

    public PlayerBox(int x, int y, int width, int height, String[] players) throws Exception {
        int playerCount = players.length;
        if (playerCount != 2 && playerCount != 4) {
            throw new Exception("Invalid player count.");
        }
        this.players = players;
        this.playerCount = playerCount;
        xPos = (playerCount == 4) ? 19 : 74;
        xAdder = (playerCount == 4) ? 66 : 86;

        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        background = new Background(width, height, "PlayerBox.png");
        add(background);

        // Init player icons
        playerIcons = new PlayerIcon[playerCount];
        for (int i = 0; i < playerCount; i++) {
            playerIcons[i] = new PlayerIcon(xPos, 49, iconPath[i], players[i]);
            add(playerIcons[i], 1, 1);
            xPos += xAdder;
        }
        playerIcons[currPlayerIdx].setIsActive(true);
    }

    /**
     * Set current player equal to next player.
     */
    public void next() throws Exception {
        playerIcons[currPlayerIdx].setIsActive(false);
        if (winnerIdx.size() == playerCount) {
            throw new Exception("All player already win.");
        }
        do {
            currPlayerIdx++;
            if (currPlayerIdx >= playerCount) {
                currPlayerIdx = 0;
            }
        } while (winnerIdx.contains(currPlayerIdx));
        playerIcons[currPlayerIdx].setIsActive(true);
    }

    /**
     * Add won player index.
     *
     * @param playerIdx
     */
    public void addWonPlayer(int playerIdx) {
        this.winnerIdx.add(playerIdx);
        this.playerIcons[playerIdx].setWinner();
    }
}
