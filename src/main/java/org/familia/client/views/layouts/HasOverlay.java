package org.familia.client.views.layouts;

import org.familia.client.views.components.overlay.Overlay;

import java.util.HashMap;

public interface HasOverlay {
    HashMap<String, Overlay> overlays = new HashMap<>();

    default Overlay getOverlay(String name, String activeOverlay) {
        if (!overlays.containsKey(name)) {
            throw new IllegalArgumentException("No " + name + " found in overlays.");
        }
        return overlays.get(name);
    }

    void addOverlayToPane(String name);

    /**
     * Add overlay listener to remove overlay with animation.
     */
    void removeOverlayFromPane();
}
