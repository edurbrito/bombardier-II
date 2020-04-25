package com.lpoo.g72;
import com.lpoo.g72.controller.SceneController;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.gui.VisualHelicopter;
import com.lpoo.g72.states.bombDropStates.CanDropBomb;

import java.io.IOException;

public class Game {
    private Gui gui;
    private SceneController controller;

    public static void main(String[] args) throws IOException, InterruptedException {
        new Game().start();
    }

    public void start() throws IOException, InterruptedException {
        gui = new Gui(100, 50, new VisualHelicopter());

        controller = new SceneController(gui);
        controller.start();
    }

    public Gui getGui() {
        return gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }
}
