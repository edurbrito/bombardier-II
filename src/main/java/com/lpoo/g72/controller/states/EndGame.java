package com.lpoo.g72.controller.states;

import com.lpoo.g72.controller.Controller;
import com.lpoo.g72.gui.Gui;

import java.io.IOException;

public class EndGame extends State{

    public EndGame(Controller controller) {
        super(controller);
    }

    @Override
    public void action(Gui.Key key) throws IOException {
        this.controller.endGame(key);
    }
}
