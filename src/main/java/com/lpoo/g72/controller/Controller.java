package com.lpoo.g72.controller;

import com.lpoo.g72.commands.*;
import com.lpoo.g72.creator.LisbonSceneCreator;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.model.Model;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Monster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    private final Gui gui;
    private Model model;

    private HelicopterController helicopterController;
    private List<MonsterController> monsterControllers;

    protected CommandInvoker commandInvoker;

    public Controller(Gui gui, Model model) {
        this.gui = gui;
        this.model = model;

        // The scene should be set in the menu then when the user chooses the city
        this.initScene();

        this.initElementControllers();

        this.commandInvoker = CommandInvoker.getInstance();
    }

    private void initScene() {
        this.gui.setScene(new LisbonSceneCreator().createScene(this.gui.getWidth(), this.gui.getHeight()));

        for(int i = 0; i< this.gui.getScene().getVisualMonsters().size(); i++){
            this.model.addMonster(new Monster(new Position(this.gui.getScene().getWidth() + new Random().nextInt(20),i*2)));
        }
    }

    private void initElementControllers() {
        this.helicopterController = new HelicopterController(this.gui.getVisualHelicopter(), this.model.getHelicopter(),this.gui.getScene().getWidth() - 1);

        this.monsterControllers = new ArrayList<>();

        for(int i = 0; i < this.model.getMonsters().size(); i++){
            this.monsterControllers.add(new MonsterController(this.gui.getScene().getVisualMonsters().get(i), this.model.getMonsters().get(i), this.gui.getScene().getWidth()-1));
            this.helicopterController.addObserver(this.monsterControllers.get(i));
        }
    }

    public void start() throws IOException {
        this.gui.draw(this.model.getHelicopter(),this.model.getMonsters(), this.model.getVerticalMissiles(), this.model.getHorizontalMissiles());
        this.run();
    }

    public void run() throws IOException {
        Gui.Key key;

        while ((key = this.gui.getKey()) != Gui.Key.QUIT) {

            this.helicopterController.run(key);

            for(MonsterController monsterController : this.monsterControllers)
                monsterController.move();

            this.commandInvoker.executeCommands();

            this.gui.draw(this.model.getHelicopter(), this.model.getMonsters(), this.model.getVerticalMissiles(), this.model.getHorizontalMissiles());
            this.gui.refreshScreen();
        }

        this.quit();

    }

    void quit() {
        try {
            this.gui.closeScreen();
        } catch (IOException e) {}
    }
}
