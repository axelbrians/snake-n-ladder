package org.familia.client.views.components.chat;

import org.familia.client.helpers.Asset;
import org.familia.client.views.components.button.CustomButton;

import java.awt.*;
import java.io.IOException;

public class ChatSendButton extends CustomButton {
    private final Image sendBtn;

    public ChatSendButton(int x, int y, int size) throws IOException {
        super(x, y, size, size, 360, "", null, Color.WHITE);

        sendBtn = Asset.getImage("SendBtn.png", size, size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(sendBtn, 0, 0, null);
    }
}
