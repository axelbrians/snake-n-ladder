package org.familia.client.views.layouts;

import org.familia.client.views.components.overlay.Overlay;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Objects;

public interface HasOverlay {
    HashMap<String, Overlay> overlays = new HashMap<>();

    default Overlay getOverlay(String name, String activeOverlay) {
        if (!overlays.containsKey(name)) {
            throw new IllegalArgumentException("No " + name + " found in overlays.");
        }
        if (!Objects.equals(activeOverlay, "")) {
            throw new IllegalArgumentException("Another overlay is already active on the panel.");
        }
        return overlays.get(name);
    }

    void addOverlayToPane(String name);

    /**
     * Add overlay listener to remove overlay with animation.
     */
    void removeOverlayFromPane();
}
