package com.lpoo.g72.controller.states;

import com.lpoo.g72.controller.Controller;
import com.lpoo.g72.gui.Gui;

import java.io.IOException;

public class GameState extends State{
    public GameState(Controller controller) {
        super(controller);
    }

    @Override
    public void action(Gui.Key key) throws IOException {
        this.controller.play(key);
    }
}
