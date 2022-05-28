package org.familia.client.providers;

import org.familia.client.views.frames.MainFrame;
import org.familia.client.views.layouts.GameLayout;

import javax.swing.*;
import java.awt.*;

public class ComponentProvider {
    public static MainFrame getFrameAncestor(Component component) {
        return (MainFrame) SwingUtilities.getAncestorOfClass(MainFrame.class, component);
    }

    public static GameLayout getGameLayoutAncestor(Component component) {
        return (GameLayout) SwingUtilities.getAncestorOfClass(GameLayout.class, component);
    }
}
