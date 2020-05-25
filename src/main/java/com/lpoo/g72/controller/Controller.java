package com.lpoo.g72.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

    private int selectedScene;
    private List<Scene> scenes;
    private List<String> menuOptions;

    Map<String,List<Integer>> highscores;

    private HelicopterController helicopterController;
    private List<MonsterController> monsterControllers;
    private CollisionController collisionController;

    protected CommandInvoker commandInvoker;

    public Controller(Gui gui, Model model) {
        this.gui = gui;
        this.model = model;

        this.state = new MenuState(this);
        this.selectedScene = 0;
        this.setScenes();

        this.collisionController = new CollisionController(gui,model);
        this.destroyedBlocks = 0;
        this.score = 0;

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
        for (Scene scene : this.scenes) {
            this.menuOptions.add(scene.getName());
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
                this.gui.refreshScreen();
            } catch (Exception e) {
                break;
            }
        }

    }

    public void menu(Gui.Key key) throws IOException {

        this.gui.drawMenu(this.selectedScene,this.menuOptions);

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

    public void play(Gui.Key key){

        if(key == Gui.Key.QUIT){
            this.state = new MenuState(this);
            this.setScenes();
        }

        this.gui.draw(this.model.getHelicopter(),this.model.getMonsters(), this.destroyedBlocks, this.score);

        this.helicopterController.run(key);

        for(MonsterController monsterController : this.monsterControllers)
            monsterController.move();

        this.commandInvoker.executeCommands();

        this.collisions();

        Helicopter helicopter = this.model.getHelicopter();
        if(helicopter.getLives() < 0 || this.collisionController.buildingsCollisions() || this.destroyedBlocks == this.gui.getScene().getSceneBlocks()){
            this.score += this.gui.getHeight() - this.helicopterController.getAltitude();
            this.addScore();
            this.state = new EndGame(this);
        }
    }

    private void collisions() {
        int blocks = this.collisionController.blocksDestroyed();
        this.destroyedBlocks += blocks;
        this.score += this.collisionController.horizontalCollisions() + 2 * blocks;
    }

    public void highscores(Gui.Key key) throws IOException {
        this.gui.drawHighscores(this.highscores);
        this.gui.refreshScreen();

        if(key == Gui.Key.QUIT){
            this.state = new MenuState(this);
        }
    }

    public void endGame(Gui.Key key){

        this.gui.draw(this.model.getHelicopter(),this.model.getMonsters(), this.destroyedBlocks, this.score);

        if(this.model.getHelicopter().getLives() < 0 || this.destroyedBlocks != this.gui.getScene().getSceneBlocks()){
            this.gui.drawMessage(this.gui.getGameOverMessage(), "#b10000","Score: " + score);
        }
        else{
            this.gui.drawMessage(this.gui.getVictoryMessage(), "#28a016","Score: " + score);
        }

        if(key == Gui.Key.QUIT){
            this.state = new MenuState(this);
            this.setScenes();
        }
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
            entry.getValue().sort(Collections.reverseOrder());
            while (entry.getValue().size() > 5){
                entry.getValue().remove(entry.getValue().size()-1);
            }
        }
    }

}
