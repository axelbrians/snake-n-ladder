package org.familia.client.views.components.overlay;

import org.familia.client.Main;
import org.familia.client.providers.ComponentProvider;
import org.familia.client.views.components.Background;
import org.familia.client.views.layouts.HasOverlay;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DiceOverlay extends Overlay {
    private final Background dice;
    private final double delay = 1.5; // Delay between fade-in and fade-out in seconds.
    private int diceNumber;

    public DiceOverlay(JLayeredPane pane) throws IOException {
        this(0, 0, Main.WIDTH, Main.HEIGHT, 0.75f, pane);
    }

    public DiceOverlay(int x, int y, int width, int height, float alpha, JLayeredPane pane) throws IOException {
        super(x, y, width, height, alpha, pane);
        this.diceNumber = 0;

        dice = new Background(405, 190, 149, 170, "Dice.png");
        add(dice);
    }

    public int getDiceNumber() {
        return diceNumber;
    }

    public void setDiceNumber(int diceNumber) {
        this.diceNumber = diceNumber;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set font
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.setFont((new Font("Source Serif Pro", Font.BOLD, 128)));

        // Draw text in middle
        FontMetrics font = g2d.getFontMetrics();
        String diceNumStr = Integer.toString(diceNumber);
        int x = (width - font.stringWidth(diceNumStr))/2;
        g2d.drawString(diceNumStr, x, 490);

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
                ((HasOverlay) ComponentProvider.getGameLayoutAncestor(this)).removeOverlayFromPane();
            }
        });
    }
}
