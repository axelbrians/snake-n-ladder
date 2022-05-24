package org.familia.client.views.components.button;

import org.familia.client.helpers.Asset;
import org.familia.client.helpers.TextWriter;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.HashMap;

public class WoodButton extends CustomButton {
    private final Image background;

    public WoodButton(int x, int y, int width, int height, String text) throws IOException {
        super(x, y, width, height, 15, text, Color.BLACK, Color.ORANGE);

        background = Asset.getImage("WoodBg.png", width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);

        // Set font
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.WHITE);
        HashMap<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.05);
        g2d.setColor(Color.WHITE);
        g2d.setFont((new Font("Corpse", Font.PLAIN, 20)).deriveFont(attributes));

        // Draw text in middle
        TextWriter.writeCustomFontInMiddle(g2d, text, width, height);
        g2d.dispose();
    }
}
