package org.familia.client.views.components;

import org.familia.client.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollSlider extends JPanel {
    private final int width;
    private final int height;
    private final int gap = 9;
    private final int sliderWidth = 6;
    private final int sliderHeight = 59;

    // Animation variable
    private int diff = 5;
    private int sliderX;
    private boolean isSliding;
    private Timer timer;

    public RollSlider(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        sliderX = (width - sliderWidth)/ 2;
        isSliding = false;

        setOpaque(false);
        setBounds(x, y - gap, width, height + gap*2);
        initAnimation(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.decode("#737373"));
        g.fillRect(sliderX, 0, sliderWidth, sliderHeight);
    }

    private void initAnimation(JPanel pane) {
        timer = new Timer(Main.DELAY, null);
        int rightBorder = width - sliderWidth;
        timer.addActionListener(ae -> {
            sliderX += diff;
            if (sliderX <= 0 || sliderX >= rightBorder) {
                diff *= -1;
            }
            pane.repaint();
        });
    }

    public void slide() {
        if (isSliding) {
            timer.stop();
            isSliding = false;
            return;
        }
        timer.start();
        isSliding = true;
    }
}
