package com.lpoo.g72.controller;

import com.lpoo.g72.commands.*;
import com.lpoo.g72.creator.LisbonSceneCreator;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.scene.visualElement.VisualElement;
import com.lpoo.g72.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SceneController {

    private final Gui gui;
    private Scene scene;

    private HelicopterController helicopterController;
    private List<MonsterController> monsterControllers;
    private List<VisualElement> visualElements;
    protected CommandInvoker commandInvoker;

    public SceneController(Gui gui) {
        this.gui = gui;
        // The scene should be set in the menu then when the user chooses the city
        this.scene = new LisbonSceneCreator().createScene(gui.getWidth(), gui.getHeight());

        this.visualElements = new ArrayList<>();
        this.monsterControllers = new ArrayList<>();

        this.helicopterController = new HelicopterController(this.scene, this.scene.getHelicopter());
        this.visualElements.add(this.helicopterController.getVisualElement());

        for(int i = 0; i < this.scene.getMonsters().size(); i++){
            this.monsterControllers.add(new MonsterController(this.scene, this.scene.getMonsters().get(i)));
            this.helicopterController.addObserver(this.monsterControllers.get(i));

            this.visualElements.add(this.monsterControllers.get(i).getVisualElement());
        }

        this.commandInvoker = CommandInvoker.getInstance();
    }

    public void start() throws IOException {
        this.gui.drawScene(this.scene, this.visualElements);
        this.run();
    }

    public void run() throws IOException {
        Gui.Key key;

        while ((key = this.gui.getKey()) != Gui.Key.QUIT) {

            this.helicopterController.run(key);

            for(MonsterController monsterController : monsterControllers)
                monsterController.move();

            commandInvoker.executeCommands();

            this.gui.drawScene(this.scene, this.visualElements);
            this.gui.refreshScreen();
        }

        quit();

    }

    void quit() {
        try {
            this.gui.closeScreen();
        } catch (IOException e) {}
    }
}
