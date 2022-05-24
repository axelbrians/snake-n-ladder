package org.familia.client.views.components.overlay;


import javax.swing.*;
import java.awt.*;

public class Overlay extends JLayeredPane {
    protected final int width;
    protected final int height;
    protected float alpha;

    public Overlay(int x, int y, int width, int height, float alpha) {
        super();

        this.width = width;
        this.height = height;
        this.alpha = alpha;

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
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width - 1, height - 1);
    }
}
