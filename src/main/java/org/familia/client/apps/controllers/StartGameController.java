package org.familia.client.apps.controllers;

import org.familia.client.views.layouts.Layout;
import org.familia.client.views.layouts.implementations.StartGameLayout;

public class StartGameController extends Controller {
    protected StartGameLayout layout;

    public StartGameController() throws Exception {
        layout = new StartGameLayout();
    }

    public Layout getLayout() {
        return layout;
    }
}
