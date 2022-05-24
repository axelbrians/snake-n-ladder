package org.familia.client.views.components.chat;

import javax.swing.*;
import java.awt.*;

public class ChatTextPane extends JPanel {
    public ChatTextArea textArea;
    public ChatScrollPane scrollPane;

    public ChatTextPane(int x, int y, int width, int height)  {
        super();
        setLayout(new GridBagLayout());
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));
        setOpaque(false);
        setBorder(null);

        textArea = new ChatTextArea();
        scrollPane = new ChatScrollPane(textArea);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
    }
}
