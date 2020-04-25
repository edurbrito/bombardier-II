package com.lpoo.g72.controller;

import com.lpoo.g72.commands.*;
import com.lpoo.g72.creator.LisbonSceneCreator;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.gui.VisualHelicopter;
import com.lpoo.g72.scene.Scene;

import java.io.IOException;

public class SceneController {

    private final Gui gui;
    private VisualHelicopter visualHelicopter;
    private Scene scene;

    private HelicopterController helicopterController;

    public SceneController(Gui gui) {
        this.gui = gui;
        // The scene should be set in the menu then when the user chooses the city
        this.scene = new LisbonSceneCreator().createScene(gui.getWidth(), gui.getHeight());
        this.visualHelicopter = new VisualHelicopter(this.scene.getHelicopter());
        this.helicopterController = new HelicopterController(this.scene, this.visualHelicopter);
    }

    public void start() throws IOException {
        this.gui.drawScene(this.scene, this.visualHelicopter);
        this.run();
    }

    public void run() throws IOException {

        while (true) {

            Gui.Key key = this.gui.getKey();

            if (key == Gui.Key.QUIT) {
                Command cmd = new QuitCommand(this.gui.getScreen());
                cmd.execute();
                break;
            }

            this.helicopterController.executeCommand(key);

            // TODO: Instead of passing a visualHelicopter, an array of visualElements (abstract class) could be created for the scene
            // To be able to also draw monsters, using a drawElement instead of a drawHelicopter, @see gui.drawHelicopter
            // Every visualElement subclass could have a form (array of chars) and a colorPallet (array of colors for each char)
            this.gui.drawScene(this.scene, this.visualHelicopter);
            this.gui.refreshScreen();
        }
    }
}
