package org.familia.client.views.components;

import org.familia.client.helpers.TextWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RollButton extends JButton {
    private final int size;
    private final int thickness = 2;
    private Color color = Color.decode("#D24B4B");

    private final Color[] colors = {
        Color.BLACK,    // Default color
        Color.ORANGE,   // Hover color
    };

    public RollButton(int x, int y, int size) {
        this.size = size;

        setBounds(x, y, size, size);
        setContentAreaFilled(false);
        setBorder(new RoundedBorder(colors[0], 360, thickness));
        setHoverEffect();
    }

    private void setHoverEffect() {
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                setBorder(new RoundedBorder(colors[1], 360, thickness));
            }
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                setBorder(new RoundedBorder(colors[0], 360, thickness));
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillOval(0, 0, size, size);

        TextWriter.writeInMiddle(g, "Roll", size, size);
    }
}
