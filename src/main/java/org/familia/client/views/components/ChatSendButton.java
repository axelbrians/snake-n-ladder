package org.familia.client.views.components;

import org.familia.client.helpers.Asset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ChatSendButton extends JButton {
    private final Image sendBtn;

    public ChatSendButton(int x, int y, int size) throws IOException {
        super();

        setOpaque(false);
        setBorder(null);
        setContentAreaFilled(false);
        setBounds(x, y, size, size);
        setPreferredSize(new Dimension(size, size));

        sendBtn = Asset.getImage("SendBtn.png", size, size);

        setHoverEffect();
    }

    private void setHoverEffect() {
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                setBorder(new RoundedBorder(Color.WHITE, 360, 2));
            }
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                setBorder(null);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(sendBtn, 0, 0, null);
    }
}
