package org.familia.client.views.components.overlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Overlay extends JLayeredPane {
    protected final int width;
    protected final int height;
    protected final float alpha;

    protected final int delay = 25; // in ms
    protected final float fadeDuration = 1.5f; // in s
    protected float diff;
    protected float currAlpha;

    public Overlay(int x, int y, int width, int height, float alpha) {
        super();

        this.width = width;
        this.height = height;
        this.alpha = alpha;
        this.currAlpha = alpha;
        diff = (alpha / fadeDuration) / delay;

        setLayout(null);
        setBorder(null);
        setOpaque(false);
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));
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

    /**
     * Add to pane with fade-in animation.
     *
     * @param pane
     * @param constraint
     * @param index
     */
    public void addToPane(JLayeredPane pane, int constraint, int index) {
        Timer timer = new Timer(delay, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                currAlpha += diff;
                repaint();
                if (currAlpha >= alpha) {
                    timer.stop();
                }
            }
        });
        currAlpha = 0;
        pane.add(this, constraint, index);
        timer.start();
    }

    /**
     * Remove from pane with fade-out animation.
     *
     * @param pane
     */
    public void removeFromPane(JLayeredPane pane) {
        Overlay overlay = this;
        Timer timer = new Timer(delay, null);

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                currAlpha -= diff;
                repaint();
                if (currAlpha <= 0) {
                    pane.remove(overlay);
                    timer.stop();
                }
            }
        });
        currAlpha = alpha;
        timer.start();
    }
}
