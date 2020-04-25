package com.lpoo.g72.controller;
import com.lpoo.g72.commands.*;
import com.lpoo.g72.gui.VisualHelicopter;
import com.lpoo.g72.scene.Element;
import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.Scene;
import com.lpoo.g72.states.BombDropStart;

import java.time.Duration;
import java.time.Instant;

import static com.lpoo.g72.gui.Gui.*;

public class HelicopterController {
    private VisualHelicopter visualHelicopter;
    private Scene scene;

    private int altitude;
    private double velocity;
    private Instant lastForwardMove;

    public HelicopterController(Scene scene, VisualHelicopter visualHelicopter){
        this.scene = scene;
        this.visualHelicopter = visualHelicopter;

        this.altitude = scene.getHelicopter().getPosition().getY();
        this.lastForwardMove = Instant.now();
        this.velocity = 0.2 * Math.pow(10,9);

        visualHelicopter.setStartBombDropProperties(0.5 * Math.pow(10,9),1);
    }

    public void executeCommand(Key key){
        Command cmd;
        this.moveForward();

        visualHelicopter.bombDropAction();

        if(key == Key.SPACE && visualHelicopter.canDropBomb()){
            visualHelicopter.setBombDropState(new BombDropStart(visualHelicopter));
        }
        if(key == Key.DOWN && isWithinDownLimit()){
            cmd = new DownCommand(scene.getHelicopter());
            cmd.execute();
        }
        else if(key == Key.UP && isWithinUpLimit()){
            cmd = new UpCommand(scene.getHelicopter());
            cmd.execute();
        }
    }

    private boolean newRound(){
        return scene.getHelicopter().getPosition().getX() == scene.getWidth()-1;
    }

    private void moveForward(){

        Instant current = Instant.now();
        Duration timePassed = Duration.between(lastForwardMove , current);

        if(newRound()){
            decreaseAltitude(scene.getHelicopter());
            altitude++;
            lastForwardMove = Instant.now();
        }

        if(timePassed.getNano() >= velocity){
            Command right = new RightCommand(scene.getHelicopter());
            right.execute();
            lastForwardMove = current;
        }
    }

    private void decreaseAltitude(Element element){
        element.setPosition(new Position(0,altitude + 1));
    }

    private boolean isWithinUpLimit(){
        return scene.getHelicopter().getPosition().getY() - 1 > altitude - 1;
    }

    private boolean isWithinDownLimit(){
        return scene.getHelicopter().getPosition().getY() - 1 < altitude + 2;
    }
}
