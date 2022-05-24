package org.familia.client.views.components.overlay;

import org.familia.client.helpers.TextWriter;
import org.familia.client.views.components.button.WoodButton;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.HashMap;

public class NetworkErrorOverlay extends Overlay {
    private final String text = "Network Error";
    private WoodButton exitBtn;

    public NetworkErrorOverlay(int x, int y, int width, int height, float alpha) throws IOException {
        super(x, y, width, height, alpha);

        exitBtn = new WoodButton(403, 406, 105, 42, "Exit");
        add(exitBtn, 1, 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set font
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.WHITE);
        HashMap<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.1);
        g2d.setFont((new Font("Source Serif Pro", Font.BOLD, 48)).deriveFont(attributes));

        // Draw text in middle
        TextWriter.writeCustomFontInMiddle(g2d, text, width, height);
        g2d.dispose();
    }
}
