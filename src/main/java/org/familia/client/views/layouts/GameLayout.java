package org.familia.client.views.layouts;

import org.familia.client.views.components.overlay.Overlay;

import javax.swing.*;
import java.util.HashMap;

public class GameLayout extends JLayeredPane {
    public HashMap<String, Overlay> overlays = new HashMap<>();
}
