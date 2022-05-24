package org.familia.client.views.components.chat;

import javax.swing.*;
import java.awt.*;

public class ChatScrollPane extends JScrollPane {
    public ChatScrollPane(ChatTextArea view)  {
        super(view);

        setOpaque(false);
        setBorder(null);
        setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        getVerticalScrollBar().setUnitIncrement(8);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        getViewport().setBackground(new Color(0, 0, 0, 0));
    }
}
