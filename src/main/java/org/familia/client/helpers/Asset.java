package org.familia.client.helpers;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Asset {
    public static final String ASSET_PATH = "src/main/java/org/familia/client/assets/";

    /**
     * Player icon path.
     * Naming format:
     *  <color>.png = base image
     *  <color>White.png = image for active player
     */
    public static final String[] ICONPATH = {
            "players/Blue",
            "players/Green",
            "players/Pink",
            "players/Yellow",
    };

    public static File getFile(String type, String name) {
        return new File(ASSET_PATH + type + "/" + name);
    }

    public static Image getImage(String name) throws IOException {
        return ImageIO.read(getFile("images", name));
    }

    public static Image getImage(String name, int width, int height) throws IOException {
        return ImageIO.read(getFile("images", name)).getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }

    public static ImageIcon getImageIcon(String name) {
        return new ImageIcon(ASSET_PATH + "images" + "/" + name);
    }
}
