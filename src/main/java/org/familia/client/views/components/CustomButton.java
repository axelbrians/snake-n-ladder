package org.familia.client.views.components;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.HashMap;

public class CustomButton extends JButton {
    private final Image background;
    private final int width;
    private final int height;
    private final String text;
    private final int thickness = 2;

    private final Color[] colors = {
            Color.BLACK,    // Default color
            Color.ORANGE,   // Hover color
    };

    public CustomButton(int x, int y, int width, int height, String text) throws IOException {
        this.width =  width;
        this.height = height;
        this.text = text;

        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        background = Asset.getImage("WoodBg.png", width, height);
        setBorder(new RoundedBorder(colors[0], 15, thickness));
        setHoverEffect();
    }

    private void setHoverEffect() {
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                setBorder(new RoundedBorder(colors[1], 15, thickness));
            }
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                setBorder(new RoundedBorder(colors[0], 15, thickness));
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Set font
        g2d.setColor(Color.WHITE);
        HashMap<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.05);
        g2d.setColor(Color.WHITE);
        g2d.setFont((new Font("Corpse", Font.PLAIN, 20)).deriveFont(attributes));

        // Draw text in middle
        FontMetrics font = g2d.getFontMetrics();
        int x = (width - font.stringWidth(text))/2;
        int y = (height + font.getHeight()/2)/2;
        g2d.drawString(text, x, y);

        g2d.dispose();
    }
}
