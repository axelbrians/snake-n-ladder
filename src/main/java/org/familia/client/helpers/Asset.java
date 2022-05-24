package org.familia.client.helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Asset {
    public static String ASSET_PATH = "src/main/java/org/familia/client/assets/";

    public static File getFile(String type, String name) {
        return new File(ASSET_PATH + type + "/" + name);
    }

    public static BufferedImage getImage(String name) throws IOException {
        return ImageIO.read(getFile("images", name));
    }

    public static ImageIcon getImageIcon(String name) {
        return new ImageIcon(ASSET_PATH + "images" + "/" + name);
    }
}
