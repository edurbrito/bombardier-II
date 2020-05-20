package com.lpoo.g72.controller.states;

import com.lpoo.g72.controller.Controller;
import com.lpoo.g72.gui.Gui;

import java.io.IOException;

public class Highscores extends State{

    public Highscores(Controller controller) {
        super(controller);
    }

    @Override
    public void action(Gui.Key key) throws IOException {
        this.controller.highscores(key);
    }
}
