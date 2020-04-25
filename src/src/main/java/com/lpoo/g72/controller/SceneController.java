package com.lpoo.g72.controller;
import com.lpoo.g72.commands.*;
import com.lpoo.g72.creator.LisbonScene;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.gui.VisualHelicopter;
import com.lpoo.g72.scene.Scene;
import org.graalvm.compiler.asm.amd64.AMD64Assembler;

import java.io.IOException;

public class SceneController{
    private final Gui gui;
    private VisualHelicopter visualHelicopter;
    private Scene scene;

    private HelicopterController helicopterController;

    public SceneController(Gui gui, VisualHelicopter visualHelicopter){
        this.gui = gui;
        // The scene should be set in the menu then when the user chooses the city
        this.scene = new LisbonScene().createScene(gui.getWidth(),gui.getHeight());
        this.helicopterController = new HelicopterController(scene,visualHelicopter);
        this.visualHelicopter = visualHelicopter;
    }

    public void start() throws IOException {
        gui.drawScene(scene);
        this.run();
    }

    public void run() throws IOException {

        while(true){

            Gui.Key key = gui.getKey();

            if(key == Gui.Key.QUIT){
                QuitCommand cmd = new QuitCommand(gui.getScreen());
                cmd.execute();
                break;
            }

            helicopterController.executeCommand(key);

            gui.drawScene(scene);
            gui.drawHelicopter(visualHelicopter,scene);
            gui.refreshScreen();
        }
    }
}
