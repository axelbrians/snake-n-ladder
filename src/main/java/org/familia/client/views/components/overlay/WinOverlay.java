package org.familia.client.views.components.overlay;

import org.familia.client.Main;
import org.familia.client.providers.ComponentProvider;
import org.familia.client.views.components.Background;
import org.familia.client.views.frames.MainFrame;
import org.familia.client.views.layouts.HasOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.HashMap;

public class WinOverlay extends Overlay {
    private final double delay = 2; // Delay between fade-in and exit in seconds.
    private final String title = "Winner";
    private final Background trophy;

    private int rank;
    private String playerName;
    private boolean currPlayer;

    public WinOverlay(JLayeredPane pane) throws IOException {
        this(0, 0, Main.WIDTH, Main.HEIGHT, 0.75f, pane);
    }

    public WinOverlay(int x, int y, int width, int height, float alpha, JLayeredPane pane) throws IOException {
        super(x, y, width, height, alpha, pane);
        this.rank = 0;
        this.playerName = "";
        this.currPlayer = false; // if true, this overlay will auto-redirect user to main menu.

        trophy = new Background(396, 261, 170, 170, "Trophy.png");
        add(trophy);
    }

    public void setWinner(String playerName, boolean currPlayer) {
        this.rank++;
        this.playerName = playerName;
        this.currPlayer = currPlayer;
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

    protected void setFadeInAction() {
        fadeIn.addActionListener(ae -> {
            currAlpha += diff;
            repaint();
            if (currAlpha >= alpha) {
                fadeIn.stop();
                try {
                    Thread.sleep((long) delay*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!currPlayer) {
                    ((HasOverlay) ComponentProvider.getGameLayoutAncestor(this)).removeOverlayFromPane();
                    return;
                }
                // Redirect to in game view.
                MainFrame frame = ComponentProvider.getFrameAncestor(this);
                try {
                    frame.setController(Main.startGameController);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
