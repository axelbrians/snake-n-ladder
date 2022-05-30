package org.familia.client.views.templates.InGame;

import org.familia.client.views.components.chat.ChatSendButton;
import org.familia.client.views.components.chat.ChatTextField;
import org.familia.client.views.components.chat.ChatTextPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ChatBox extends JPanel {
    private final int width;
    private final int height;
    private final int arc = 15;

    private final static String newline = "\n";
    private ChatTextField textField;
    private ChatSendButton sendButton;
    private ChatTextPane textPane;

    public ChatBox(int x, int y, int width, int height) throws IOException {
        super();
        this.width = width;
        this.height = height;

        setLayout(null);
        setOpaque(false);
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        textField = new ChatTextField(11, 152, 221, 30);
        sendButton = new ChatSendButton(237, 149, 34);
        textPane = new ChatTextPane(11, 18, 258, 117);

        setActionListener();
        textField.addKeyListener(new ChatTextFieldAdapter());

        add(textField);
        add(sendButton);
        add(textPane);
    }

    public void enableChatbox() {
        textField.setEnabled(true);
        textPane.scrollPane.setEnabled(true);
        sendButton.setIsActive(true);
    }

    public void disableChatbox() {
        textField.setEnabled(false);
        textPane.scrollPane.setEnabled(false);
        sendButton.setIsActive(false);
    }

    private void sendText() {
        String text = textField.getText();
        textPane.textArea.append(text + newline);
        textPane.textArea.setCaretPosition(textPane.textArea.getDocument().getLength());
        textField.setText("");
        textPane.repaint();
    }

    private void setActionListener() {
        sendButton.addActionListener((ActionEvent e) -> sendText());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(0, 0, width - 1, height - 1, arc, arc);

        super.paintComponent(g);
    }

    /**
     * Keymap for textfield.
     */
    class ChatTextFieldAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent ke) {
            int keyCode = ke.getKeyCode();

            switch (keyCode) {
                case KeyEvent.VK_ENTER:
                    sendText();
                    break;
            }
        }
    }
}
