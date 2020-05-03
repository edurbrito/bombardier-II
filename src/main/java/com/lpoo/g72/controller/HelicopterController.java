package com.lpoo.g72.controller;
import com.lpoo.g72.commands.directional.DownCommand;
import com.lpoo.g72.commands.directional.RightCommand;
import com.lpoo.g72.commands.directional.UpCommand;
import com.lpoo.g72.gui.visualElement.VisualHelicopter;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Helicopter;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.lpoo.g72.gui.Gui.*;

public class HelicopterController extends ElementController implements Observable{

    private List<Observer> observerList;

    public HelicopterController(VisualHelicopter visualHelicopter, Helicopter helicopter, int maxWidth){
        super(visualHelicopter, helicopter);

        this.maxWidth = maxWidth;
        this.altitude = this.getElementY();

        this.lastForwardMove = Instant.now();
        this.velocity = 0.2 * Math.pow(10,9);

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
    }

    protected void move(){

        Instant current = Instant.now();
        Duration timePassed = Duration.between(this.lastForwardMove , current);

        if(timePassed.getNano() >= this.velocity){

            if(this.newRound())
                this.decreaseAltitude();

            this.commandInvoker.addCommand(new RightCommand(this.element));

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
