package org.familia.client.helpers;

public class Coordinate {
    private final double baseX;
    private final double baseY;
    public double x;
    public double y;

    public Coordinate(double x, double y) {
        this.baseX = x;
        this.baseY = y;
        this.x = x;
        this.y = y;
    }

    public void moveTo(int tileNumber) {
        int idx = tileNumber-1;
        boolean isLeftToRight = ((idx / 10) % 2 == 0);
        int xFactor = (idx % 10);
        if (!isLeftToRight) {
            xFactor = 9-xFactor;
        }
        x = baseX + (59 * xFactor);
        y = baseY - (59 * (idx / 10));
    }
}
