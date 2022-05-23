package org.familia.client.views;

import org.familia.client.views.components.ChatSendButton;
import org.familia.client.views.components.ChatTextField;
import org.familia.client.views.components.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ChatBox extends JPanel {
    private final int width;
    private final int height;
    private final int arc = 15;

    private final static String newline = "\n";
    protected ChatTextField textField;
    protected ChatSendButton sendButton;
    protected JTextArea textArea;

    public ChatBox(int x, int y, int width, int height) throws IOException {
        this.width = width;
        this.height = height;

        setLayout(null);
        setOpaque(false);
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        textField = new ChatTextField(11, 152, 221, 30);
        sendButton = new ChatSendButton(237, 149, 34);

        add(textField);
        add(sendButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(0, 0, width - 1, height - 1, arc, arc);
    }
}
