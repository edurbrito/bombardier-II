package com.lpoo.g72.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.lpoo.g72.commands.CommandInvoker;
import com.lpoo.g72.controller.states.*;
import com.lpoo.g72.creator.LisbonSceneCreator;
import com.lpoo.g72.creator.OportoSceneCreator;
import com.lpoo.g72.creator.RandomSceneCreator;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.gui.Scene;
import com.lpoo.g72.model.Model;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Missile;
import com.lpoo.g72.model.element.Monster;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.*;

public class Controller implements Observer{

    private final Gui gui;
    private Model model;
    private State state;

    private int destroyedBlocks;
    private int score;
    private final int pointsPerBlock;
    private final int pointsPerMonster;

    private int selectedScene;
    private List<Scene> scenes;
    private List<String> menuOptions;

    Map<String,List<Integer>> highscores;

    private HelicopterController helicopterController;
    private List<MonsterController> monsterControllers;

    protected CommandInvoker commandInvoker;

    public Controller(Gui gui, Model model) {
        this.gui = gui;
        this.model = model;

        this.state = new MenuState(this);
        this.selectedScene = 0;
        this.setScenes();

        this.destroyedBlocks = 0;
        this.score = 0;
        this.pointsPerBlock = 2;
        this.pointsPerMonster = 1;

        this.highscores = new HashMap<>();
        this.readHighscores();

        this.commandInvoker = CommandInvoker.getInstance();
    }

    private void setScenes(){
        this.scenes = new ArrayList<>();
        this.scenes.add(new OportoSceneCreator().createScene(this.gui.getWidth(), this.gui.getHeight()));
        this.scenes.add(new LisbonSceneCreator().createScene(this.gui.getWidth(), this.gui.getHeight()));
        this.scenes.add(new RandomSceneCreator().createScene(this.gui.getWidth(), this.gui.getHeight()));

        this.setMenuOptions();
    }

    private void setMenuOptions(){
        this.menuOptions = new ArrayList<>();
        for(int i = 0; i< this.scenes.size(); i++){
           this.menuOptions.add(this.scenes.get(i).getName());
        }
        this.menuOptions.add("Highscores");
    }

    private void initModel(){
        int bombs , missiles;

        bombs = (int) Math.round(Math.pow(this.scenes.get(selectedScene).getSceneBlocks(),1/5.0));
        missiles = 2;

        this.model.reset( new Helicopter(new Position(0,1),bombs,missiles));
    }

    private void initScene(Scene scene) {
        this.gui.setScene(scene);

        for(int i = 0; i < this.gui.getScene().getVisualMonsters().size(); i++){
            this.model.addMonster(new Monster(new Position(this.gui.getScene().getWidth() + new Random().nextInt(20),i%2)));
        }

        this.score = 0;
        this.destroyedBlocks = 0;
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

    public void menu(Gui.Key key) throws IOException {

        this.gui.drawMenu(this.selectedScene,this.menuOptions);
        this.gui.refreshScreen();

        if(key == Gui.Key.QUIT){
            this.quit();
        }
        else if(key == Gui.Key.UP && this.selectedScene > 0){
            this.selectedScene --;
        }
        else if(key == Gui.Key.DOWN && this.selectedScene < this.scenes.size()){
            this.selectedScene ++;
        }
        else if(key == Gui.Key.ENTER && this.selectedScene < this.scenes.size()){
            this.initModel();
            this.initScene(this.scenes.get(this.selectedScene));
            this.state = new GameState(this);
        }
        else if(key == Gui.Key.ENTER && this.selectedScene == this.scenes.size()){
            this.state = new Highscores(this);
        }
    }

    public void play(Gui.Key key) throws IOException {

        if(key == Gui.Key.QUIT){
            this.state = new MenuState(this);
        }

        this.gui.draw(this.model.getHelicopter(),this.model.getMonsters(), this.destroyedBlocks, this.score);

        this.helicopterController.run(key);

        for(MonsterController monsterController : this.monsterControllers)
            monsterController.move();

        this.commandInvoker.executeCommands();

        this.horizontalCollisions();
        this.verticalCollisions();

        Helicopter helicopter = this.model.getHelicopter();
        if(helicopter.getLives() < 0 || this.buildingsCollisions() || this.destroyedBlocks == this.gui.getScene().getSceneBlocks()){
            this.addScore();
            this.state = new EndGame(this);
            return;
        }

        this.gui.refreshScreen();
    }

    public void highscores(Gui.Key key) throws IOException {
        this.gui.drawHighscores(this.highscores);
        this.gui.refreshScreen();

        if(key == Gui.Key.QUIT){
            this.state = new MenuState(this);
        }
    }

    public void endGame(Gui.Key key) throws IOException {

        this.gui.draw(this.model.getHelicopter(),this.model.getMonsters(), this.destroyedBlocks, this.score);

        if(this.model.getHelicopter().getLives() < 0 || this.destroyedBlocks != this.gui.getScene().getSceneBlocks()){
            this.gui.drawMessage(this.gui.getGameOverMessage(), "#b10000","Score: " + score);
        }
        else{
            this.gui.drawMessage(this.gui.getVictoryMessage(), "#28a016","Score: " + score);
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
        this.writeHighscores();
        this.gui.closeScreen();
        throw new IOException();
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

    private void readHighscores(){

        try {
            Type type = new TypeToken<Map<String, List<Integer>>>(){}.getType();
            this.highscores = new Gson().fromJson(new FileReader("src/main/java/com/lpoo/g72/highscores.json"), type);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeHighscores(){
        try {
            Writer writer = new FileWriter("src/main/java/com/lpoo/g72/highscores.json");

            new Gson().toJson(this.highscores, writer);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addScore(){

        this.highscores.get(this.menuOptions.get(this.selectedScene)).add(this.score);

        for (Map.Entry<String, List<Integer>> entry : this.highscores.entrySet()) {
            Collections.sort(entry.getValue(), Collections.reverseOrder());
            while (entry.getValue().size() > 5){
                entry.getValue().remove(entry.getValue().size()-1);
            }
        }
    }

}
