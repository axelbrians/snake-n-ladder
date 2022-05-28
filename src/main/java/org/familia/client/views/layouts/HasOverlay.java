package org.familia.client.views.layouts;

import org.familia.client.views.components.overlay.Overlay;

import java.util.HashMap;

public interface HasOverlay {
    HashMap<String, Overlay> overlays = new HashMap<>();

    default Overlay getOverlay(String name) {
        if (!overlays.containsKey(name)) {
            throw new IllegalArgumentException("No " + name + " found in overlays.");
        }
        return overlays.get(name);
    }

    default void addOverlayToPane(String name) {
        Overlay overlay = getOverlay(name);
        addOverlayToPane(overlay);
    }

    void addOverlayToPane(Overlay overlay);
    
    void removeOverlayFromPane();
}
