package org.familia.client.views.components.button;

import org.familia.client.helpers.Asset;
import org.familia.client.views.components.RoundedBorder;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageRoundButton extends OvalButton {
    private BufferedImage image;

    private Border defaultBorder;
    private Border hoverBorder;

    public ImageRoundButton(int x, int y, int size, String imageName) {
        super();
        setBounds(x, y, size, size);
        setSize(new Dimension(size, size));
        setBorderThickness(0);

        defaultBorder = null;
        hoverBorder = new RoundedBorder(Color.ORANGE, 15, 2);
        setHoverEffect();

        try {
            image = (BufferedImage) Asset.getImage(imageName);
        } catch (IOException exception) {
            exception.printStackTrace();
            image = null;
        }
    }

    protected void setHoverEffect() {
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                setBorder(hoverBorder);
            }
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                setBorder(defaultBorder);
            }
        });
    }

    @Override
    protected BufferedImage getBackgroundImage() {
        return image;
    }
}
