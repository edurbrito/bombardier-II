package com.lpoo.g72.controller;

import com.lpoo.g72.commands.*;
import com.lpoo.g72.creator.LisbonSceneCreator;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.gui.visualElement.VisualElement;
import com.lpoo.g72.model.Model;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Element;
import com.lpoo.g72.model.element.Missile;
import com.lpoo.g72.model.element.Monster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller implements Observer{

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

        for(int i = 0; i< this.gui.getScene().getNumMonsters(); i++){
            this.model.addMonster(new Monster(new Position(this.gui.getScene().getWidth() + new Random().nextInt(20),i*2)));
        }
    }

    private void initElementControllers() {
        this.helicopterController = new HelicopterController(this.gui.getVisualHelicopter(), this.model.getHelicopter(), this.gui.getScene().getWidth() - 1);

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

            this.verticalMissileCollisions();
            this.horizontalMissileCollisions();

            this.gui.draw(this.model.getHelicopter(), this.model.getMonsters(), this.model.getVerticalMissiles(), this.model.getHorizontalMissiles());
            this.gui.refreshScreen();
        }

        this.quit();

    }

    private void verticalMissileCollisions(){
        List<Missile> verticalMissiles = this.model.getHelicopter().getVerticalMissiles();

        int x, y;

        for(int i = 0; i < verticalMissiles.size(); i++){
            if(verticalMissiles.get(i).isActive()){
                y = verticalMissiles.get(i).getPosition().getY() + this.gui.verticalMissileSize() - 1;
                x = verticalMissiles.get(i).getPosition().getX();

                if(this.gui.removedBuilding(x,y)){
                    verticalMissiles.get(i).deactivate();
                }
            }
        }
    }

    private void horizontalMissileCollisions(){
        List<Missile> horizontalMissiles = this.model.getHelicopter().getHorizontalMissiles();
        List<Monster> monsters = this.model.getMonsters();

        Position missilePos;

        for(int i = 0; i < horizontalMissiles.size(); i++){
            if(horizontalMissiles.get(i).isActive()){
                missilePos = new Position(horizontalMissiles.get(i).getPosition().getX() + this.gui.horizontalMissileSize() - 1,
                        horizontalMissiles.get(i).getPosition().getY());

                for(int j = 0; j < monsters.size(); j++){
                    if(this.horizontalCollision(missilePos, monsters.get(j).getPosition()) && this.model.getMonsters().get(j).isAlive()){
                        this.model.removeMonster(j);
                        horizontalMissiles.get(i).deactivate();
                        break;
                    }
                }
            }
        }
    }

    private boolean horizontalCollision(Position pos1, Position pos2){
        return pos1.equals(pos2) || pos1.equals(pos2.right()) || pos1.equals(pos2.left());
    }

    void quit() {
        try {
            this.gui.closeScreen();
        } catch (IOException e) {}
    }

    @Override
    public void update(int info) {
        this.gui.refreshVisualMonsters();
    }
}
