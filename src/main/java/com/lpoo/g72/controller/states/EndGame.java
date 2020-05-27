package com.lpoo.g72.controller.states;

import com.lpoo.g72.controller.Controller;
import com.lpoo.g72.gui.Gui;

public class EndGame extends State {

    public EndGame(Controller controller) {
        super(controller);
    }

    @Override
    public void action(Gui.Key key) {
        this.controller.endGame(key);
    }
}
