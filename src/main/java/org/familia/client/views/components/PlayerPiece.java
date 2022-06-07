package org.familia.client.views.components;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PlayerPiece extends JPanel {
    private final int playerIdx;
    private final Image icon;

    private int tileNumber;
    private int x;
    private int y;

    public PlayerPiece(int playerIdx,
                   String iconPath,
                   int x,
                   int y
    ) throws IOException {
        this(playerIdx, 0, iconPath, x, y, 27, 41);
        setVisible(false);
        setOpaque(false);
    }

    public PlayerPiece(int playerIdx,
                       int tileNumber,
                       String iconPath,
                       int x,
                       int y,
                       int width,
                       int height
    ) throws IOException {
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        this.playerIdx = playerIdx;
        this.tileNumber = tileNumber;
        this.x = 0;
        this.y = 0;
        this.icon = Asset.getImage(iconPath + ".png", width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(icon, x, y, null);
    }

    public int[] getCoordinate() {
        return (new int[] { this.x, this.y });
    }

    public int getPlayerIdx() {
        return this.playerIdx;
    }

    public int getTileNumber() {
        return this.tileNumber;
    }
}
