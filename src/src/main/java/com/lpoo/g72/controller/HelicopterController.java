package com.lpoo.g72.controller;
import com.lpoo.g72.commands.*;
import com.lpoo.g72.gui.VisualHelicopter;
import com.lpoo.g72.scene.Helicopter;
import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.Scene;
import com.lpoo.g72.states.BombDropStart;

import java.time.Duration;
import java.time.Instant;

import static com.lpoo.g72.gui.Gui.*;

public class HelicopterController {

    private VisualHelicopter visualHelicopter;
    private Helicopter helicopter;

    private int maxWidth;
    private int altitude;
    private double velocity;
    private Instant lastForwardMove;

    public HelicopterController(Scene scene, VisualHelicopter visualHelicopter){
        this.visualHelicopter = visualHelicopter;
        this.helicopter = scene.getHelicopter();

        this.maxWidth = scene.getWidth() - 1;
        this.altitude = getHelicopterY();

        this.lastForwardMove = Instant.now();
        this.velocity = 0.2 * Math.pow(10,9);

        visualHelicopter.setStartBombDropProperties(0.5 * Math.pow(10,9),1);
    }

    public void executeCommand(Key key){
        this.moveForward();

        visualHelicopter.bombDropAction();

        if(key == Key.SPACE && visualHelicopter.canDropBomb()){
            visualHelicopter.setBombDropState(new BombDropStart(visualHelicopter));
        }

        Command cmd;
        if(key == Key.DOWN && isWithinDownLimit()){
            cmd = new DownCommand(this.helicopter);
            cmd.execute();
        }
        else if(key == Key.UP && isWithinUpLimit()){
            cmd = new UpCommand(this.helicopter);
            cmd.execute();
        }
    }

    private void moveForward(){

        Instant current = Instant.now();
        Duration timePassed = Duration.between(this.lastForwardMove , current);

        if(timePassed.getNano() >= velocity){

            if(this.newRound())
                this.decreaseAltitude();

            Command right = new RightCommand(this.helicopter);
            right.execute();

            this.visualHelicopter.wingRotation();

            this.lastForwardMove = current;
        }
    }

    private boolean newRound(){
        return getHelicopterX() == this.maxWidth;
    }

    private void decreaseAltitude(){
        this.helicopter.setPosition(new Position(0, ++this.altitude));
    }

    private boolean isWithinUpLimit(){
        return this.getHelicopterY() > this.altitude - 1 && this.getHelicopterY() > 0;
    }

    private boolean isWithinDownLimit(){
        return this.getHelicopterY() < this.altitude + 1;
    }

    private int getHelicopterX() {
        return this.helicopter.getPosition().getX();
    }

    private int getHelicopterY() {
        return this.helicopter.getPosition().getY();
    }
}
