package org.familia.client.views.components.overlay;

import org.familia.client.helpers.TextWriter;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;

public class LoadingOverlay extends Overlay {
    private final String text = "Loading...";

    public LoadingOverlay(int x, int y, int width, int height, float alpha) {
        super(x, y, width, height, alpha);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Set font
        g2d.setColor(Color.WHITE);
        HashMap<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.1);
        g2d.setColor(Color.WHITE);
        g2d.setFont((new Font("Source Serif Pro", Font.BOLD, 48)).deriveFont(attributes));

        // Draw text in middle
        FontMetrics font = g2d.getFontMetrics();
        int x = (width - font.stringWidth(text))/2;
        int y = (height + font.getHeight()/2)/2;
        g2d.drawString(text, x, y);

        g2d.dispose();
    }
}
