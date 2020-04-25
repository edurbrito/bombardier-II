package com.lpoo.g72.controller;
import com.lpoo.g72.commands.Command;
import com.lpoo.g72.commands.DownCommand;
import com.lpoo.g72.commands.RightCommand;
import com.lpoo.g72.commands.UpCommand;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.scene.Element;
import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.Scene;

import java.time.Duration;
import java.time.Instant;

public class HelicopterController {
    private  Gui gui;
    private Scene scene;

    private int altitude;
    private double velocity;
    private Instant lastForwardMove;

    public HelicopterController(Gui gui, Scene scene){
        this.scene = scene;
        this.gui = gui;

        this.altitude = scene.getHelicopter().getPosition().getY();
        this.lastForwardMove = Instant.now();
        this.velocity = 0.2 * Math.pow(10,9);

        gui.setStartBombDropProperties(0.5 * Math.pow(10,9),1);
    }

    public void executeCommand(Gui.Key key){
        Command cmd;
        this.moveForward();

        gui.changeWing(key);

        if(key == Gui.Key.DOWN && isWithinDownLimit()){
            cmd = new DownCommand(scene.getHelicopter());
            cmd.execute();
        }
        else if(key == Gui.Key.UP && isWithinUpLimit()){
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
