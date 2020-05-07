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

public class HelicopterController extends ElementController implements Observable{

    private List<Observer> observerList;
    private int maxHeight;
    private double missileDeltaTime;

    public HelicopterController(VisualHelicopter visualHelicopter, Helicopter helicopter, int maxWidth, int maxHeight){
        super(visualHelicopter, helicopter);

        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.altitude = this.getElementY();

        this.lastForwardMove = Instant.now();
        this.deltaTime = 0.15 * Math.pow(10,9);
        this.missileDeltaTime = 0.14 * Math.pow(10,9);
        this.observerList = new ArrayList<>();
    }

    public void run(Key key){

        this.move();

        if(key == Key.DOWN && isWithinDownLimit()){
            this.commandInvoker.addCommand(new DownCommand(element));
        }
        else if(key == Key.UP && isWithinUpLimit()){
            this.commandInvoker.addCommand(new UpCommand(element));
        }
        else if(key == Key.SPACE && !newRound()){
            Helicopter helicopter = (Helicopter) this.element;
            this.commandInvoker.addCommand(new DropMissile(helicopter));
        }
        else if(key == Key.RIGHT && !newRound()){
            Helicopter helicopter = (Helicopter) this.element;
            this.commandInvoker.addCommand(new ShootMissile(helicopter));
        }
    }

    protected void move(){

        Instant current = Instant.now();
        Duration timePassed = Duration.between(this.lastForwardMove , current);

        if(timePassed.getNano() >= this.deltaTime){

            if(this.newRound() && this.missilesEnded())
                this.decreaseAltitude();

            this.commandInvoker.addCommand(new RightCommand(this.element));

            this.visualElement.animation();

            this.lastForwardMove = current;
        }

        if(timePassed.getNano() >= this.missileDeltaTime){
            this.moveMissiles();
        }
    }

    private void decreaseAltitude(){
        this.element.setPosition(new Position(0, ++this.altitude));
        this.notifyObservers();
    }

    private boolean newRound(){
        return this.getElementX() >= this.maxWidth;
    }

    private boolean isWithinUpLimit(){
        return this.getElementY() > this.altitude - 1 && this.getElementY() > 0;
    }

    private boolean isWithinDownLimit(){
        return this.getElementY() < this.altitude + 1;
    }

    private void moveMissiles(){
        Helicopter helicopter = (Helicopter) this.element;
        for(Missile missile: helicopter.getHorizontalMissiles()){
            this.commandInvoker.addCommand(new RightCommand(missile));
        }

        for(Missile missile: helicopter.getVerticalMissiles()){
            if(missile.getPosition().getY() >= this.maxHeight - 2){
                missile.setExploded();
            }
            else{
                this.commandInvoker.addCommand(new DownCommand(missile));
            }
        }
    }

    private boolean missilesEnded(){
        Helicopter helicopter = (Helicopter) this.element;
        boolean missilesEnded = true;
        for(Missile missile : helicopter.getVerticalMissiles()){
            if(!missile.hasExploded())
                missilesEnded = false;
        }

        if(missilesEnded){
            helicopter.resetMissiles();
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
