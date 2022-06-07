package org.familia.client.providers;

import org.familia.client.views.frames.MainFrame;
import org.familia.client.views.layouts.Layout;

import javax.swing.*;
import java.awt.*;

public class ComponentProvider {
    public static MainFrame getFrameAncestor(Component component) {
        return (MainFrame) SwingUtilities.getAncestorOfClass(MainFrame.class, component);
    }

    public static Layout getGameLayoutAncestor(Component component) {
        return (Layout) SwingUtilities.getAncestorOfClass(Layout.class, component);
    }
}
