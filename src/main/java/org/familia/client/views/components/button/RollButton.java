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

        if (isActive) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
        g2d.setColor(Color.BLACK);
        g2d.fillOval(0, 0, width, height);
    }
}
