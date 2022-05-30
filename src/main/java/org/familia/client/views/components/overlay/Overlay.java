package org.familia.client.views.components.overlay;

import org.familia.client.Main;

import javax.swing.*;
import java.awt.*;

public class Overlay extends JLayeredPane {
    protected final JLayeredPane pane;
    protected final int width;
    protected final int height;
    protected final float alpha;
    protected final Timer fadeIn;
    protected final Timer fadeOut;

    protected final float fadeDuration = 1.5f; // in s
    protected float diff;
    protected float currAlpha;

    public Overlay(int x, int y, int width, int height, float alpha, JLayeredPane pane) {
        super();
        this.pane = pane;
        this.width = width;
        this.height = height;
        this.alpha = alpha;
        this.currAlpha = alpha;
        diff = (alpha / fadeDuration) / Main.DELAY;

        setLayout(null);
        setBorder(null);
        setOpaque(false);
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        // Set timer
        fadeIn = new Timer(Main.DELAY, null);
        fadeOut = new Timer(Main.DELAY, null);
        setFadeInAction();
        setFadeOutAction();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, currAlpha));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);
    }

    protected void setFadeInAction() {
        fadeIn.addActionListener(ae -> {
            currAlpha += diff;
            repaint();
            if (currAlpha >= alpha) {
                fadeIn.stop();
            }
        });
    }

    protected void setFadeOutAction() {
        fadeOut.addActionListener(ae -> {
            currAlpha -= diff;
            repaint();
            if (currAlpha <= 0) {
                pane.remove(this);
                fadeOut.stop();
            }
        });
    }

    /**
     * Add to pane with fade-in animation.
     *
     * @param constraint
     * @param index
     */
    public void addToPane(int constraint, int index) {
        currAlpha = 0;
        pane.add(this, constraint, index);
        fadeIn.start();
    }

    /**
     * Remove from pane with fade-out animation.
     */
    public void removeFromPane() {
        currAlpha = alpha;
        fadeOut.start();
    }
}
