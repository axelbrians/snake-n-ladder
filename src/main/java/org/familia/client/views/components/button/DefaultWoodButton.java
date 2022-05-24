package org.familia.client.views.components.button;

import java.io.IOException;

public class DefaultWoodButton extends WoodButton {
    public static int WIDTH = 112;
    public static int HEIGHT = 52;

    public DefaultWoodButton(int x, int y, String text) throws IOException {
        super(x, y, WIDTH, HEIGHT, text);

    }
}
