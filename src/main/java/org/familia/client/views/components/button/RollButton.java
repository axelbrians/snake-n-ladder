package org.familia.client.views.components.button;

import org.familia.client.helpers.TextWriter;
import java.awt.*;

public class RollButton extends CustomButton {
    private Color bgColor = Color.decode("#D24B4B");

    public RollButton(int x, int y, int size) {
        super(x, y, size, size, 360, "Roll", Color.BLACK, Color.ORANGE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(bgColor);
        g.fillOval(0, 0, width, height);

        TextWriter.writeInMiddle(g, "Roll", width, height);
    }
}
