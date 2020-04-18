package com.lpoo.g72;

import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.scene.Scene;

import java.io.IOException;

public class Game {
    private Scene scene;
    private Gui gui;

    public static void main(String[] args) throws IOException {
        new Game().start();
    }

    public void start() throws IOException {
        gui = new Gui(100,50);

        while(!gui.mainMenu()){
            System.out.println("Still in Main Menu");
        }
    }

    public Scene getScene() {
        return this.scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Gui getGui() {
        return this.gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }
}
