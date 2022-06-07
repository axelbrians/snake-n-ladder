package org.familia.client.providers;

import org.familia.client.networks.SocketConnection;
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

    public static DataProvider getDataProvider(Component component) {
        return getFrameAncestor(component).dataProvider;
    }

    public static SocketConnection getSocketConnection(Component component) {
        return getFrameAncestor(component).socketConnection;
    }
}
