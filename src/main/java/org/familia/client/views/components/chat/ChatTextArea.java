package org.familia.client.views.components.chat;

import javax.swing.*;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class ChatTextArea extends JTextArea {
    public ChatTextArea() {
        setOpaque(false);
        setLineWrap(true);
        setEditable(false);
        setFont(new Font("Source Serif Pro", Font.PLAIN, 14));
        setForeground(Color.WHITE);
        setBorder(null);
        setFocusable(false);
        setEnabled(false);

        setCaret(new DefaultCaret() {
            public void paint(Graphics g) {}
        });
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        super.paintComponent(g2d);
    }
}
