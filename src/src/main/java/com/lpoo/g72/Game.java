package com.lpoo.g72;
import com.lpoo.g72.controller.SceneController;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.gui.VisualHelicopter;

import java.io.IOException;

public class Game {
    private Gui gui;
    private SceneController controller;

    public static void main(String[] args) throws IOException, InterruptedException {
        new Game().start();
    }

    public void start() throws IOException, InterruptedException {
        VisualHelicopter visualHelicopter = new VisualHelicopter();
        gui = new Gui(100, 50);

        controller = new SceneController(gui,visualHelicopter);
        controller.start();
    }

    public Gui getGui() {
        return gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }
}
