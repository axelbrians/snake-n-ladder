package org.familia.client.views.components.overlay;

import org.familia.client.Main;
import org.familia.client.views.components.Background;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.HashMap;

public class WinOverlay extends Overlay {
    private final String title = "Winner";
    private final Background trophy;

    private int rank;
    private String playerName;

    public WinOverlay() throws IOException {
        this(0, 0, Main.WIDTH, Main.HEIGHT, 0.75f);
    }

    public WinOverlay(int x, int y, int width, int height, float alpha) throws IOException {
        super(x, y, width, height, alpha);
        this.rank = 0;
        this.playerName = "";

        trophy = new Background(396, 261, 170, 170, "Trophy.png");
        add(trophy);
    }

    public void setWinner(String playerName) {
        this.rank++;
        this.playerName = playerName;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        HashMap<TextAttribute, Object> attributes = new HashMap<>();
        FontMetrics font;
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.WHITE);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        int x = 0;
        int y = 0;

        // Draw title
        attributes.put(TextAttribute.TRACKING, 0.1);
        g2d.setFont((new Font("Source Serif Pro", Font.BOLD, 64)).deriveFont(attributes));
        font = g2d.getFontMetrics();
        x = (width - font.stringWidth(title))/2;
        y = 181 + (80 + font.getHeight()/2)/2;
        g2d.drawString(title, x, y);

        // Draw player name
        g2d.setFont((new Font("Source Serif Pro", Font.BOLD, 24)).deriveFont(attributes));
        font = g2d.getFontMetrics();
        x = (width - font.stringWidth(playerName))/2;
        y = 510 + (30 + font.getHeight()/2)/2;
        g2d.drawString(playerName, x, y);

        // Draw rank
        String rank = "Rank " + this.rank;
        attributes.replace(TextAttribute.TRACKING, 0.2);
        g2d.setFont((new Font("Source Serif Pro", Font.BOLD, 40)).deriveFont(attributes));
        font = g2d.getFontMetrics();
        x = (width - font.stringWidth(rank))/2;
        y = 431 + (50 + font.getHeight()/2)/2;
        g2d.drawString(rank, x, y);

        g2d.dispose();
    }
}
