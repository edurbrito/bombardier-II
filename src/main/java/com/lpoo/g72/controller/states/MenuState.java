package com.lpoo.g72.controller.states;

import com.lpoo.g72.controller.Controller;
import com.lpoo.g72.gui.Gui;

import java.io.IOException;

public class MenuState extends State {

    public MenuState(Controller controller) {
        super(controller);
    }

    @Override
    public void action(Gui.Key key) throws IOException {
        this.controller.menu(key);
    }
}
