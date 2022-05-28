package org.familia.client.views.layouts;

import org.familia.client.Main;
import org.familia.client.views.components.Background;
import org.familia.client.views.components.overlay.NetworkErrorOverlay;
import org.familia.client.views.components.overlay.Overlay;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class Layout extends JLayeredPane {
    public HashMap<String, Overlay> overlays = new HashMap<>();
    protected Background background;

    public Layout() throws IOException {
        setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));

        overlays.put("networkError", new NetworkErrorOverlay());
    }
}
