package com.lpoo.g72.controller;
import com.lpoo.g72.command.Command;
import com.lpoo.g72.command.DownCommand;
import com.lpoo.g72.command.RightCommand;
import com.lpoo.g72.command.UpCommand;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.gui.VisualHelicopter;
import com.lpoo.g72.scene.Element;
import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.Scene;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class HelicopterController {
    private  Gui gui;
    private Scene scene;
    int altitude;
    long velocity;
    Instant start;

    public HelicopterController(Gui gui, Scene scene){
        this.scene = scene;
        this.gui = gui;
        this.altitude = scene.getHelicopter().getPosition().getY();
        this.velocity = 200000000;
        this.start = Instant.now();
    }

    public void executeCommand(Gui.Key key) throws IOException, InterruptedException {
        Command cmd;
        this.moveForward();

        this.launchBomb(key);

        if(key == Gui.Key.DOWN && isWithinDownLimit()){
            cmd = new DownCommand(scene.getHelicopter());
            cmd.execute();
        }
        else if(key == Gui.Key.UP && isWithinUpLimit()){
            cmd = new UpCommand(scene.getHelicopter());
            cmd.execute();
        }
    }

    private void launchBomb(Gui.Key key) throws InterruptedException, IOException {

    }

    boolean newRound(){
        return scene.getHelicopter().getPosition().getX() == scene.getWidth()-1;
    }

    void moveForward(){

        Instant current = Instant.now();
        Duration timePassed = Duration.between(start , current);

        if(newRound()){
            decreaseAltitude(scene.getHelicopter());
            altitude++;
            start = Instant.now();
        }

        if(timePassed.getNano() >= velocity){
            Command right = new RightCommand(scene.getHelicopter());
            right.execute();
            start = current;
        }
    }

    void decreaseAltitude(Element element){
        element.setPosition(new Position(0,altitude + 1));
    }

    boolean isWithinUpLimit(){
        return scene.getHelicopter().getPosition().getY() - 1 > altitude - 1;
    }

    boolean isWithinDownLimit(){
        return scene.getHelicopter().getPosition().getY() - 1 < altitude + 2;
    }
}
