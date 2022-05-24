package org.familia.client.views.components.button;

import org.familia.client.views.components.RoundedBorder;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {
    protected final int width;
    protected final int height;
    protected final int borderThickness;
    protected final int arc;
    protected final String text;
    protected final Border defaultBorder;
    protected final Border hoverBorder;
    protected boolean isActive;

    public CustomButton(int x,
                        int y,
                        int width,
                        int height,
                        int arc,
                        String text
    ) {
        this(x, y, width, height, 2, arc, text, Color.BLACK, Color.ORANGE);
    }

    public CustomButton(int x,
                        int y,
                        int width,
                        int height,
                        int arc,
                        String text,
                        Color defaultBorderColor,
                        Color hoverBorderColor
    ) {
        this(x, y, width, height, 2, arc, text, defaultBorderColor, hoverBorderColor);
    }

    public CustomButton(int x,
                        int y,
                        int width,
                        int height,
                        int borderThickness,
                        int arc,
                        String text,
                        Color defaultBorderColor,
                        Color hoverBorderColor
    ) {
        this.width = width;
        this.height = height;
        this.borderThickness = borderThickness;
        this.arc = arc;
        this.text = text;
        this.defaultBorder = (defaultBorderColor == null)
                ? null
                : new RoundedBorder(defaultBorderColor, arc, borderThickness);
        this.hoverBorder = (hoverBorderColor == null)
                ? null
                : new RoundedBorder(hoverBorderColor, arc, borderThickness);
        this.isActive = true;

        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));
        setContentAreaFilled(false);
        setBorder(new RoundedBorder(defaultBorderColor, arc, borderThickness));
        setHoverEffect();
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    protected void setHoverEffect() {
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (!isActive) return;
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                setBorder(hoverBorder);
            }
            public void mouseExited(MouseEvent e) {
                if (!isActive) return;
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                setBorder(defaultBorder);
            }
        });
    }
}
