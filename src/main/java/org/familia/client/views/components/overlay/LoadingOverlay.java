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

        // Set font
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.WHITE);
        HashMap<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.1);
        g2d.setColor(Color.WHITE);
        g2d.setFont((new Font("Source Serif Pro", Font.BOLD, 48)).deriveFont(attributes));

        // Draw text in middle
        TextWriter.writeCustomFontInMiddle(g2d, text, width, height);
        g2d.dispose();
    }
}
