package org.familia.client.views.components.overlay;

import org.familia.client.Main;
import org.familia.client.helpers.TextWriter;
import org.familia.client.views.components.button.WoodButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.HashMap;

public class NetworkErrorOverlay extends Overlay implements ClosableFrame {
    private final String text = "Network Error";
    private WoodButton exitBtn;

    public NetworkErrorOverlay(JLayeredPane pane) throws IOException {
        this(0, 0, Main.WIDTH, Main.HEIGHT, 0.75f, pane);
    }

    public NetworkErrorOverlay(int x, int y, int width, int height, float alpha, JLayeredPane pane) throws IOException {
        super(x, y, width, height, alpha, pane);
        exitBtn = new WoodButton(403, 406, 105, 42, "Exit");
        add(exitBtn, 1, 1);
    }

    public void setCloseFrameAction(JFrame frame) {
        exitBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set font
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.WHITE);
        HashMap<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.1);
        g2d.setFont((new Font("Source Serif Pro", Font.BOLD, 48)).deriveFont(attributes));

        // Draw text in middle
        TextWriter.writeCustomFontInMiddle(g2d, text, width, height);
        g2d.dispose();
    }
}
