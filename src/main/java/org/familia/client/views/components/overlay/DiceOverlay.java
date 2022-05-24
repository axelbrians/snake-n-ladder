package org.familia.client.views.components.overlay;

import org.familia.client.views.components.Background;

import java.awt.*;
import java.io.IOException;

public class DiceOverlay extends Overlay {
    private final Background dice;
    private int diceNumber;

    public DiceOverlay(int x, int y, int width, int height, float alpha) throws IOException {
        super(x, y, width, height, alpha);
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
}
