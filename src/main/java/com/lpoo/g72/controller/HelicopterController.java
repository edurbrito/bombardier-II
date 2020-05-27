package com.lpoo.g72.controller;

import com.lpoo.g72.commands.DropMissile;
import com.lpoo.g72.commands.ShootMissile;
import com.lpoo.g72.commands.directional.DownCommand;
import com.lpoo.g72.commands.directional.RightCommand;
import com.lpoo.g72.commands.directional.UpCommand;
import com.lpoo.g72.gui.visualElement.VisualHelicopter;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Missile;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.lpoo.g72.gui.Gui.Key;

public class HelicopterController extends ElementController implements Observable {

    private VisualHelicopter visualHelicopter;
    private Helicopter helicopter;

    private List<Observer> observerList;
    private int maxHeight;
    private double missileDeltaTime;

    public HelicopterController(VisualHelicopter visualHelicopter, Helicopter helicopter, int maxWidth, int maxHeight){
        super(maxWidth, helicopter.getY(),0.12 * Math.pow(10,9));

        this.helicopter = helicopter;
        this.visualHelicopter = visualHelicopter;

        this.maxHeight = maxHeight;
        this.missileDeltaTime = 0.11 * Math.pow(10,9);

        this.observerList = new ArrayList<>();
    }

    public void run(Key key){

        this.move();

        if(key == Key.DOWN && isWithinDownLimit()){
            this.commandInvoker.addCommand(new DownCommand(this.helicopter));
        }
        else if(key == Key.UP && isWithinUpLimit()){
            this.commandInvoker.addCommand(new UpCommand(this.helicopter));
        }
        else if(key == Key.SPACE && !newRound()){
            this.commandInvoker.addCommand(new DropMissile(this.helicopter));
        }
        else if(key == Key.RIGHT && !newRound()){
            this.commandInvoker.addCommand(new ShootMissile(this.helicopter));
        }
    }

    protected void move(){

        Instant current = Instant.now();
        Duration timePassed = Duration.between(this.lastForwardMove , current);

        if(timePassed.getNano() >= this.deltaTime){

            if(this.newRound() && this.missilesEnded())
                this.decreaseAltitude();

            this.commandInvoker.addCommand(new RightCommand(this.helicopter));

            this.visualHelicopter.animation();

            this.lastForwardMove = current;
        }

        if(timePassed.getNano() >= this.missileDeltaTime){
            this.moveMissiles();
        }
    }

    private void decreaseAltitude(){
        this.helicopter.setPosition(new Position(0, ++this.altitude));
        this.notifyObservers();
    }

    private boolean newRound(){
        return this.helicopter.getX() >= this.maxWidth + 1;
    }

    private boolean isWithinUpLimit(){
        return this.helicopter.getY() > this.altitude - 1 && this.helicopter.getY() > 0;
    }

    private boolean isWithinDownLimit(){
        return this.helicopter.getY() < this.altitude + 1;
    }

    private void moveMissiles(){

        List<Missile> horizontalMissiles = this.helicopter.getHorizontalMissiles();

        for(Missile missile : horizontalMissiles){
            this.commandInvoker.addCommand(new RightCommand(missile));
        }

        List<Missile> verticalMissiles = this.helicopter.getVerticalMissiles();

        for(Missile missile : verticalMissiles){
            if(missile.getY() >= this.maxHeight - 2){
                missile.setExploded();
            }
            else{
                this.commandInvoker.addCommand(new DownCommand(missile));
            }
        }
    }

    private boolean missilesEnded(){
        boolean missilesEnded = true;

        List<Missile> verticalMissiles = this.helicopter.getVerticalMissiles();

        for(Missile missile : verticalMissiles){
            if(!missile.hasExploded())
                missilesEnded = false;
        }

        if(missilesEnded){
            this.helicopter.resetMissiles();
        }

        return missilesEnded;
    }

    @Override
    public void addObserver(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer: this.observerList)
            observer.update(this.altitude);
    }
}
