package com.lpoo.g72;

import com.lpoo.g72.controller.Controller;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.model.Model;

import java.io.IOException;

public class Game {

    private Gui gui;
    private Model model;
    private Controller controller;

    public static void main(String[] args) throws IOException{
        new Game().start();
    }

    public void start() throws IOException{
        this.gui = new Gui(100, 50);
        this.model = new Model();
        this.controller = new Controller(this.gui, this.model);
        this.controller.run();
    }

    public Gui getGui() {
        return this.gui;
    }

    public Model getModel() {
        return model;
    }

    public Controller getController() {
        return controller;
    }
}
