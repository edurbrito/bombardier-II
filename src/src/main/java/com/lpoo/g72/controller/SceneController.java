package com.lpoo.g72.controller;

import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.scene.Scene;

import java.io.IOException;

public class SceneController{
    private final Gui gui;
    private Scene scene;

    public SceneController(Gui gui, Scene scene) throws IOException {
        this.gui = gui;
        this.scene = scene;
        scene.addObserver(gui);
    }

    public void init() throws IOException {
        gui.drawScene(scene);
    }
}
