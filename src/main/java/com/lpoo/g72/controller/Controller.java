package com.lpoo.g72.controller;

import com.lpoo.g72.commands.CommandInvoker;
import com.lpoo.g72.creator.LisbonSceneCreator;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.model.Model;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Missile;
import com.lpoo.g72.model.element.Monster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    private final Gui gui;
    private Model model;

    private int destroyedBlocks;
    private float pointsPerBlock;
    private float pointsPerMonster;

    private HelicopterController helicopterController;
    private List<MonsterController> monsterControllers;

    protected CommandInvoker commandInvoker;

    public Controller(Gui gui, Model model) {
        this.gui = gui;
        this.model = model;
        this.model.setHelicopter( new Helicopter(new Position(0,1), 50, 3));

        // The scene should be set in the menu then when the user chooses the city
        this.initScene();
        this.initElementControllers();

        this.destroyedBlocks = 0;
        this.pointsPerBlock = 0.2f;
        this.pointsPerMonster = 0.4f;

        this.commandInvoker = CommandInvoker.getInstance();
    }

    private void initScene() {
        this.gui.setScene(new LisbonSceneCreator().createScene(this.gui.getWidth(), this.gui.getHeight()));

        for(int i = 0; i< this.gui.getScene().getVisualMonsters().size(); i++){
            this.model.addMonster(new Monster(new Position(this.gui.getScene().getWidth() + new Random().nextInt(20),i*2)));
        }
    }

    private void initElementControllers() {
        this.helicopterController = new HelicopterController(this.gui.getVisualHelicopter(), this.model.getHelicopter(),this.gui.getScene().getWidth() - 1, this.gui.getScene().getHeight() - 5);

        this.monsterControllers = new ArrayList<>();

        for(int i = 0; i < this.model.getMonsters().size(); i++){
            this.monsterControllers.add(new MonsterController(this.gui.getScene().getVisualMonsters().get(i), this.model.getMonsters().get(i), this.gui.getScene().getWidth()-1));
            this.helicopterController.addObserver(this.monsterControllers.get(i));
        }
    }

    public void start() throws IOException {
        this.gui.draw(this.model.getHelicopter(),this.model.getMonsters(), this.destroyedBlocks);
        this.run();
    }

    public void run() throws IOException {
        Gui.Key key;

        while ((key = this.gui.getKey()) != Gui.Key.QUIT) {

            this.helicopterController.run(key);

            for(MonsterController monsterController : this.monsterControllers)
                monsterController.move();

            this.commandInvoker.executeCommands();

            this.monsterCollision();
            this.horizontalCollisions();
            this.verticalCollisions();

            this.gui.draw(this.model.getHelicopter(), this.model.getMonsters(), destroyedBlocks);
            this.gui.refreshScreen();
        }

        this.quit();

    }

    private void horizontalCollisions(){
        List<Missile> horizontalMissiles = this.model.getHelicopter().getHorizontalMissiles();
        List<Monster> monsters = this.model.getMonsters();

        for(Missile missile : horizontalMissiles){
            for(Monster monster : monsters){
                if(horizontalCollisionChecker(missile.getPosition(),monster.getPosition()) && monster.isAlive()){
                    missile.setExploded();
                    monster.kill();
                    this.model.increaseScore( this.pointsPerMonster);
                }
            }
        }
    }

    private boolean horizontalCollisionChecker(Position pos1, Position pos2){
        return pos1.equals(pos2) || pos1.equals(pos2.right()) || pos1.equals(pos2.right().right())|| pos1.equals(pos2.left());
    }

    private void verticalCollisions(){
        int blocks = 0;

        List<Missile> verticalMissiles = this.model.getHelicopter().getVerticalMissiles();

        for(Missile missile : verticalMissiles){
            if(!missile.hasExploded()){
                int x = missile.getX();
                int y = missile.getY() % (this.gui.getScene().getBuildings().length - 2);

                for(int i = 0; i < 3; i++){
                    blocks += this.gui.getScene().removeBuilding( x, y + i);

                    this.model.increaseScore(blocks * this.pointsPerBlock);

                    this.destroyedBlocks += blocks;
                }
            }
        }
    }

    private void monsterCollision(){
        List<Monster> monsters = this.model.getMonsters();
        Helicopter helicopter = this.model.getHelicopter();

        for(Monster monster : monsters){
            if(horizontalCollisionChecker(helicopter.getPosition(),monster.getPosition()) && !monster.isResting() && monster.isAlive()){
                helicopter.loseLife();
                monster.rest();
            }
        }
    }

    void quit() {
        try {
            this.gui.closeScreen();
        } catch (IOException e) {}
    }
}
