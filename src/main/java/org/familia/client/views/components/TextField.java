package org.familia.client.views.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextField extends JTextField {
    private final int width;
    private final int height;
    private final int arc;
    private final String placeholder;

    public TextField(int x, int y, int width, int height, int arc, String placeholder) {
        super();
        this.width = width;
        this.height = height;
        this.arc = arc;
        this.placeholder = placeholder;

        setLayout(null);
        setBorder(null);
        setOpaque(false);
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        setHorizontalAlignment(JTextField.CENTER);
        if (getText().length() == 0) {
            setPlaceholderText();
        }
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setFocusText();
            }

            @Override
            public void focusLost(FocusEvent e) {
                setPlaceholderText();
            }
        });
    }

    private void setPlaceholderText() {
        if (getText().length() != 0) {
            return;
        }

        setText(placeholder);
        setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
        setForeground(Color.decode("#5E5E5E"));
    }

    private void setFocusText() {
        if (getText().length() != 0 && !isTextPlaceholder()) {
            return;
        }

        setText("");
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
    }

    public boolean isTextPlaceholder() {
        return getText().equals(placeholder);
    }
}
