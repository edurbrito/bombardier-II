package com.lpoo.g72;

import com.lpoo.g72.controller.Controller;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.model.Model;

import java.io.IOException;

public class Game {

    private Gui gui;
    private Model model;
    private Controller controller;

    public static void main(String[] args) throws IOException, InterruptedException {
        new Game().start();
    }

    public void start() throws IOException, InterruptedException {
        this.gui = new Gui(100, 50);
        this.model = new Model();
        this.controller = new Controller(this.gui, this.model);
        this.controller.start();
    }

    public Gui getGui() {
        return this.gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }
}
