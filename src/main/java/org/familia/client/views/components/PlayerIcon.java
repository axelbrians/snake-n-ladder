package org.familia.client.views.components;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.HashMap;

public class PlayerIcon extends JPanel {
    private final Image icon;
    private String playerName;
    private final int iconWidth = 27;
    private final int iconHeight = 41;
    private final int textAreaWidth = 49;
    private final int textAreaHeight = 18;

    public PlayerIcon(int x, int y, String iconPath, String playerName) throws IOException {
        setOpaque(false);
        setBounds(x, y, textAreaWidth, iconHeight + textAreaHeight);
        setPreferredSize(new Dimension(textAreaWidth, iconHeight + textAreaHeight));

        this.playerName = playerName;

        icon = Asset.getImage(iconPath, iconWidth, iconHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        writePlayerName(g);

        int x = (textAreaWidth - iconWidth)/2;
        g.drawImage(icon, x, 0, null);
    }

    private void writePlayerName(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        HashMap<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.01);
        g2d.setColor(Color.WHITE);
        g2d.setFont((new Font("Source Serif Pro", Font.BOLD, 12)).deriveFont(attributes));
        FontMetrics font = g2d.getFontMetrics();

        int stringWidth = font.stringWidth(playerName);
        int x = (textAreaWidth - stringWidth)/2;
        int y = iconHeight + font.getHeight();
        g2d.drawString(playerName, x, y);
    }
}
