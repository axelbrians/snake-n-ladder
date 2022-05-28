package org.familia.client.views.components;

import org.familia.client.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollSlider extends JPanel {
    private final int width;
    private final int gap = 9;
    private final int sliderWidth = 6;
    private final int sliderHeight = 59;
    private final int[] xSection;

    // Animation variable
    private int diff = 20;
    private int sliderX;
    private boolean isSliding;
    private Timer timer;

    public RollSlider(int x, int y, int width, int height) {
        this.width = width;
        sliderX = (width - sliderWidth)/ 2;
        isSliding = false;
        xSection = new int[] { 124, 63, 0 };

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
                if (sliderX < 0) {
                    sliderX = 0;
                } else if (sliderX > rightBorder) {
                    sliderX = rightBorder;
                }
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

    /**
     * Return roll section based on current slider x position.
     *
     * @return int
     */
    public int getSection() {
        int section  = 2;
        for (int x : xSection) {
            if (sliderX >= x) {
                break;
            }
            section--;
        }
        return section;
    }
}
