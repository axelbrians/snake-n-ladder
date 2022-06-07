package org.familia.client.views.components;

import org.familia.client.helpers.Asset;
import org.familia.client.helpers.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PlayerPiece extends JPanel {
    private final int playerIdx;
    private final Image icon;
    private Coordinate coordinate;

    private int tileNumber;
    private int x;
    private int y;

    public PlayerPiece(int playerIdx,
                   String iconPath,
                   int x,
                   int y,
                   int boardSize
    ) throws IOException {
        this(playerIdx, 0, iconPath, x, y, boardSize, 27, 41);
        setVisible(false);
        setOpaque(false);
    }

    public PlayerPiece(int playerIdx,
                       int tileNumber,
                       String iconPath,
                       int x,
                       int y,
                       int boardSize,
                       int width,
                       int height
    ) throws IOException {
        setBounds(0, 0, boardSize, boardSize);
        setPreferredSize(new Dimension(width, height));

        this.playerIdx = playerIdx;
        this.tileNumber = tileNumber;
        this.coordinate = new Coordinate(x, y);
        this.icon = Asset.getImage(iconPath + ".png", width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(icon, coordinate.x, coordinate.y, null);
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public int getPlayerIdx() {
        return this.playerIdx;
    }

    public int getTileNumber() {
        return this.tileNumber;
    }

    public void moveTo(int tileNumber) {
        int idx = tileNumber-1;
        boolean isLeftToRight = ((idx / 10) % 2 == 0);
        int xFactor = (idx % 10);
        if (!isLeftToRight) {
            xFactor = 9-xFactor;
        }
        coordinate.x = coordinate.baseX + (59 * xFactor);
        coordinate.y = coordinate.baseY - (59 * (idx / 10));
        setVisible(true);
        repaint();
    }
}
