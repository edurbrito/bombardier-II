package com.lpoo.g72.controller.states;

import com.lpoo.g72.controller.Controller;
import com.lpoo.g72.gui.Gui;

public class GameState extends State{

    public GameState(Controller controller) {
        super(controller);
    }

    @Override
    public void action(Gui.Key key) {
        this.controller.play(key);
    }
}
