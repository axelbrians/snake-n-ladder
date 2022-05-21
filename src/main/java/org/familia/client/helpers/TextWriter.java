package org.familia.client.helpers;

import java.awt.*;

public class TextWriter {
    public static void writeInMiddle(Graphics g, String text, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Set font
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Source Serif Pro", Font.BOLD, 20));
        FontMetrics font = g2d.getFontMetrics();

        // Draw text in middle
        int x = (width - font.stringWidth(text))/2;
        int y = (height + font.getHeight()/2)/2;
        g2d.drawString(text, x, y);

        g2d.dispose();
    }
}
