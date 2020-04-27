package com.lpoo.g72.controller;
import com.lpoo.g72.commands.*;
import com.lpoo.g72.commands.directional.DownCommand;
import com.lpoo.g72.commands.directional.RightCommand;
import com.lpoo.g72.commands.directional.UpCommand;
import com.lpoo.g72.scene.visualElement.VisualHelicopter;
import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.Scene;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.lpoo.g72.gui.Gui.*;

public class HelicopterController extends ElementController implements Observable{

    private List<Observer> observerList;

    public HelicopterController(Scene scene, VisualHelicopter visualHelicopter){
        super(visualHelicopter);

        this.maxWidth = scene.getWidth() - 1;
        this.altitude = this.getElementY();

        this.lastForwardMove = Instant.now();
        this.velocity = 0.2 * Math.pow(10,9);

        this.observerList = new ArrayList<>();

    }

    public void executeCommand(Key key){

        this.move();

        Command cmd;
        if(key == Key.DOWN && isWithinDownLimit()){
            cmd = new DownCommand(this.element);
            cmd.execute();
        }
        else if(key == Key.UP && isWithinUpLimit()){
            cmd = new UpCommand(this.element);
            cmd.execute();
        }
    }

    protected void move(){

        Instant current = Instant.now();
        Duration timePassed = Duration.between(this.lastForwardMove , current);

        if(timePassed.getNano() >= velocity){

            if(this.newRound())
                this.decreaseAltitude();

            Command right = new RightCommand(this.element);
            right.execute();

            this.visualElement.animation();

            this.lastForwardMove = current;
        }
    }

    private void decreaseAltitude(){
        this.element.setPosition(new Position(0, ++this.altitude));
        this.notifyObservers();
    }

    private boolean newRound(){
        return this.getElementX() == this.maxWidth;
    }

    private boolean isWithinUpLimit(){
        return this.getElementY() > this.altitude - 1 && this.getElementY() > 0;
    }

    private boolean isWithinDownLimit(){
        return this.getElementY() < this.altitude + 1;
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
