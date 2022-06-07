package org.familia.client.helpers;

public class Coordinate {
    public final int baseX;
    public final int baseY;
    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.baseX = x;
        this.baseY = y;
        this.x = x;
        this.y = y;
    }
}
