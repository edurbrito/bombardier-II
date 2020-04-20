package com.lpoo.g72;
import com.lpoo.g72.controller.SceneController;
import com.lpoo.g72.creator.LisbonScene;
import com.lpoo.g72.creator.RandomScene;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.scene.Scene;

import java.io.IOException;

public class Game {
    private Gui gui;
    private Scene scene;
    private SceneController controller;

    public static void main(String[] args) throws IOException, InterruptedException {
        new Game().start();
    }

    public void start() throws IOException, InterruptedException {
        scene = new LisbonScene().createScene(100,50);
        gui = new Gui(scene.getWidth(), scene.getHeight());
        controller = new SceneController(gui,scene);
        controller.execute();
    }
}
