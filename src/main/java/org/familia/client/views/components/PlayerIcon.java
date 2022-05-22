package org.familia.client.views.components;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.HashMap;

public class PlayerIcon extends JPanel {
    private final Image icon;
    private final Image iconBordered;
    private final String playerName;
    private final String printedName;
    private final int iconWidth = 27;
    private final int iconHeight = 41;
    private final int textAreaWidth = 49;
    private final int textAreaHeight = 18;
    private boolean isActive;

    public PlayerIcon(int x, int y, String iconPath, String playerName) throws IOException {
        setOpaque(false);
        setBounds(x, y, textAreaWidth, iconHeight + textAreaHeight);
        setPreferredSize(new Dimension(textAreaWidth, iconHeight + textAreaHeight));

        this.playerName = playerName;
        printedName = (playerName.length() > 7)
                ? playerName.substring(0,5) + "..."
                : playerName;
        isActive = false;

        icon = Asset.getImage(iconPath + ".png", iconWidth, iconHeight);
        iconBordered = Asset.getImage(iconPath + "White.png", iconWidth, iconHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        writePlayerName(g);

        int x = (textAreaWidth - iconWidth)/2;
        g.drawImage((isActive) ? iconBordered : icon, x, 0, null);
    }

    private void writePlayerName(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        HashMap<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.01);
        g2d.setColor(Color.WHITE);
        g2d.setFont((new Font("Source Serif Pro", Font.BOLD, 12)).deriveFont(attributes));
        FontMetrics font = g2d.getFontMetrics();

        int stringWidth = font.stringWidth(printedName);
        int x = (textAreaWidth - stringWidth)/2;
        int y = iconHeight + font.getHeight();
        g2d.drawString(printedName, x, y);
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
