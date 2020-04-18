package com.lpoo.g72;
import com.lpoo.g72.gui.Gui;

import java.io.IOException;

public class Game {
    private Gui gui;

    public static void main(String[] args) throws IOException, InterruptedException {
        new Game().start();
    }

    public void start() throws IOException, InterruptedException {
        gui = new Gui(100,50);

        while(!gui.mainMenu()){
            // System.out.println("Still in Main Menu");
        }

        gui.draw();
    }

    public Gui getGui() {
        return this.gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }
}
