package com.lpoo.g72.controller;
import com.lpoo.g72.command.Command;
import com.lpoo.g72.command.DownCommand;
import com.lpoo.g72.command.RightCommand;
import com.lpoo.g72.command.UpCommand;
import com.lpoo.g72.creator.LisbonScene;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.scene.Element;
import com.lpoo.g72.scene.Helicopter;
import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.Scene;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class SceneController{
    private final Gui gui;
    private Scene scene;

    private HelicopterController helicopterController;

    public SceneController(Gui gui){
        this.gui = gui;
        // The scene should be set in the menu then when the user chooses the city
        this.scene = new LisbonScene().createScene(gui.getWidth(),gui.getHeight());
        this.helicopterController = new HelicopterController(gui,scene);
    }

    public void start() throws IOException {
        gui.drawScene(scene);
        this.run();
    }

    public void run() throws IOException {

        while(true){
            helicopterController.executeCommand();
            gui.drawScene(scene);
        }
    }
}
