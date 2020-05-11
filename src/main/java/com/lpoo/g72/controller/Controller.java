package com.lpoo.g72.controller;

import com.lpoo.g72.commands.CommandInvoker;
import com.lpoo.g72.controller.states.EndGame;
import com.lpoo.g72.controller.states.GameState;
import com.lpoo.g72.controller.states.MenuState;
import com.lpoo.g72.controller.states.State;
import com.lpoo.g72.creator.LisbonSceneCreator;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.gui.Scene;
import com.lpoo.g72.model.Model;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Missile;
import com.lpoo.g72.model.element.Monster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller implements Observer{

    private final Gui gui;
    private Model model;
    private State state;

    private int destroyedBlocks;
    private int score;
    private final int pointsPerBlock;
    private final int pointsPerMonster;

    private HelicopterController helicopterController;
    private List<MonsterController> monsterControllers;

    protected CommandInvoker commandInvoker;

    public Controller(Gui gui, Model model) {
        this.gui = gui;
        this.model = model;

        this.state = new MenuState(this);

        this.destroyedBlocks = 0;
        this.score = 0;
        this.pointsPerBlock = 2;
        this.pointsPerMonster = 1;

        this.commandInvoker = CommandInvoker.getInstance();
    }

    public void resetModel(){
        this.model.setHelicopter( new Helicopter(new Position(0,1),6,2));
        this.model.deleteMonsters();
    }

    public void initScene(Scene scene) {
        this.gui.setScene(scene);

        for(int i = 0; i < this.gui.getScene().getVisualMonsters().size(); i++){
            this.model.addMonster(new Monster(new Position(this.gui.getScene().getWidth() + new Random().nextInt(20),i%2)));
        }

        this.initElementControllers();
    }

    private void initElementControllers() {
        this.helicopterController = new HelicopterController(this.gui.getVisualHelicopter(), this.model.getHelicopter(),this.gui.getScene().getWidth() - 1, this.gui.getScene().getHeight() - 5);

        this.monsterControllers = new ArrayList<>();

        for(int i = 0; i < this.model.getMonsters().size(); i++){
            this.monsterControllers.add(new MonsterController(this.gui.getScene().getVisualMonsters().get(i), this.model.getMonsters().get(i), this.gui.getScene().getWidth()-1));
            this.helicopterController.addObserver(this.monsterControllers.get(i));
        }

        this.helicopterController.addObserver(this);
    }

    public void run(){

        while (true) {
            try {
                this.state.action(this.gui.getKey());
            } catch (Exception e) {
                break;
            }
        }
    }

    public void play(Gui.Key key) throws IOException {

        this.gui.draw(this.model.getHelicopter(),this.model.getMonsters(), this.destroyedBlocks, this.score);

        this.helicopterController.run(key);

        for(MonsterController monsterController : this.monsterControllers)
            monsterController.move();

        this.commandInvoker.executeCommands();

        this.horizontalCollisions();
        this.verticalCollisions();

        Helicopter helicopter = this.model.getHelicopter();
        if(helicopter.getLives() < 0 || this.buildingsCollisions()){
            this.state = new EndGame(this);
            return;
        }

        this.gui.draw(this.model.getHelicopter(), this.model.getMonsters(), this.destroyedBlocks, this.score);
        this.gui.refreshScreen();


        if(key == Gui.Key.QUIT){
            this.state = new MenuState(this);
        }
    }

    public void menu(Gui.Key key) throws IOException {
        this.resetModel();
        this.gui.drawMenu();
        this.gui.refreshScreen();

        if(key == Gui.Key.QUIT){
            this.quit();
        }
        else if(key == Gui.Key.SPACE){
            this.initScene(new LisbonSceneCreator().createScene(this.gui.getWidth(), this.gui.getHeight()));
            this.changeState(new GameState(this));
        }
    }

    public void endGame(Gui.Key key) throws IOException {
        if(this.model.getHelicopter().getLives() < 0){
            this.gui.drawMessage(this.gui.getGameOverMessage(), "#b10000","Score: " + score);
        }

        this.gui.refreshScreen();

        if(key == Gui.Key.QUIT){
            this.state = new MenuState(this);
        }
    }

    private void horizontalCollisions(){
        List<Missile> horizontalMissiles = this.model.getHelicopter().getHorizontalMissiles();
        List<Monster> monsters = this.model.getMonsters();
        Helicopter helicopter = this.model.getHelicopter();

        for(Monster monster : monsters){
            for(Missile missile : horizontalMissiles){
                if(horizontalCollisionChecker(missile.getPosition(),monster.getPosition()) && monster.isAlive()){
                    missile.setExploded();
                    monster.kill();
                    this.score += this.pointsPerMonster;
                }
            }

            if(horizontalCollisionChecker(helicopter.getPosition(),monster.getPosition()) && monster.isAlive()){
                helicopter.loseLife();
                monster.kill();
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
            if(!missile.hasExploded() && missile.isActive()){
                int x = missile.getX();
                int y = missile.getY() % (this.gui.getScene().getBuildings().length - 2);

                for(int i = 0; i < 3; i++){
                    blocks += this.gui.getScene().removeBuilding( x, y + i);
                }
            }
        }
        this.destroyedBlocks += blocks;
        this.score += this.pointsPerBlock * blocks;
    }

    private boolean buildingsCollisions(){
        Helicopter helicopter = this.model.getHelicopter();
        int heliSize = this.gui.getVisualHelicopter().getForm().length - 1;
        if(this.gui.getScene().removeBuilding(helicopter.getX() + heliSize,helicopter.getY()) > 0){
            return true;
        }
        return false;
    }

    void quit() throws IOException {
        this.gui.closeScreen();
        throw new IOException();
    }

    public void changeState(State state) {
        this.state = state;
    }

    @Override
    public void update(int info){
        try{
            this.gui.drawMessage(this.gui.getNewRoundMessage(),"#0000b1","Current Altitude: " + (this.gui.getHeight() - info));
            this.gui.refreshScreen();
            Thread.sleep(800);
        } catch (Exception e){

        }
    }
}
