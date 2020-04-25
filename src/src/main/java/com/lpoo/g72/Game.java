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
        this.gui = new Gui(100, 50);

        this.controller = new SceneController(this.gui);
        this.controller.start();
    }

    public Gui getGui() {
        return this.gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public SceneController getController() {
        return this.controller;
    }

    public void setController(SceneController controller) {
        this.controller = controller;
    }
}
