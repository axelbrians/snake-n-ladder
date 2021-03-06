package org.familia.client.views.components.chat;

import javax.swing.*;
import java.awt.*;

public class ChatTextField extends JTextField {
    private final int width;
    private final int height;
    private final int arc = 6;
    private final String placeholder;

    public ChatTextField(int x, int y, int width, int height) {
        this(x, y, width, height, "Type message here");
    }

    public ChatTextField(int x, int y, int width, int height, String placeholder) {
        super();
        this.width = width;
        this.height = height;
        this.placeholder = placeholder;

        setLayout(null);
        setBorder(null);
        setOpaque(false);
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));
        setFont(new Font("Source Serif Pro", Font.PLAIN, 15));
        setForeground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(0, 0, width - 1, height - 1, arc, arc);

        super.paintComponent(g);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }
        g2d.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
        g2d.setColor(Color.decode("#5E5E5E"));
        g2d.drawString(placeholder, getInsets().left, (height + g.getFontMetrics().getHeight()/2)/2);
    }
}
