package com.lpoo.g72.controller;
import com.lpoo.g72.commands.DropMissile;
import com.lpoo.g72.commands.directional.DownCommand;
import com.lpoo.g72.commands.directional.RightCommand;
import com.lpoo.g72.commands.ShootMissile;
import com.lpoo.g72.commands.directional.UpCommand;
import com.lpoo.g72.gui.visualElement.VisualElement;
import com.lpoo.g72.gui.visualElement.VisualHelicopter;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Missile;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.lpoo.g72.gui.Gui.*;

public class HelicopterController extends ElementController implements Observable{

    private List<Observer> observerList;
    double horizontalMissileTime;

    public HelicopterController(VisualHelicopter visualHelicopter, Helicopter helicopter, int maxWidth){
        super(visualHelicopter, helicopter);

        this.maxWidth = maxWidth;
        this.altitude = this.getElementY();

        this.lastForwardMove = Instant.now();
        this.movingTime = 0.2 * Math.pow(10,9);
        this.horizontalMissileTime= 0.18 * Math.pow(10,9);

        this.observerList = new ArrayList<>();
    }

    public void run(Key key){

        this.move();

        if(key == Key.DOWN && this.isWithinDownLimit()){
            this.commandInvoker.addCommand(new DownCommand(this.element));
        }
        else if(key == Key.UP && this.isWithinUpLimit()){
            this.commandInvoker.addCommand(new UpCommand(this.element));
        }
        else if(key == Key.SPACE && this.hasVerticalMissiles()){
            Helicopter helicopter = (Helicopter)this.element;
            this.commandInvoker.addCommand(new DropMissile(helicopter));
        }
        else if(key == Key.RIGHT && this.hasHorizontalMissiles()){
            Helicopter helicopter = (Helicopter)this.element;
            this.commandInvoker.addCommand(new ShootMissile(helicopter));
        }
    }

    protected void move(){
        Instant current = Instant.now();
        Duration timePassed = Duration.between(this.lastForwardMove , current);

        if(timePassed.getNano() >= this.movingTime){

            if(this.newRound()){
                this.decreaseAltitude();
                Helicopter helicopter = (Helicopter)this.element;
                helicopter.resetMissiles();
            }

            this.commandInvoker.addCommand(new RightCommand(this.element));

            this.visualElement.animation();

            this.moveVerticalMissiles();

            this.lastForwardMove = current;
        }

        moveHorizontalMissiles(timePassed);
    }

    private void moveVerticalMissiles() {
        Helicopter helicopter = (Helicopter)this.element;

        for(int i = 0; i < helicopter.getVerticalMissiles().size(); i++){
            if(helicopter.getVerticalMissiles().get(i).isActive())
                this.commandInvoker.addCommand(new DownCommand(helicopter.getVerticalMissiles().get(i)));
        }
    }

    private void moveHorizontalMissiles(Duration timePassed){
        if(timePassed.getNano() >= this.horizontalMissileTime){
            Helicopter helicopter = (Helicopter)this.element;

            for(int i = 0; i < helicopter.getHorizontalMissiles().size(); i++){
                if(helicopter.getHorizontalMissiles().get(i).isActive()){
                    this.commandInvoker.addCommand(new RightCommand(helicopter.getHorizontalMissiles().get(i)));
                    this.visualElement.animation();
                }
            }
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

    private boolean hasVerticalMissiles(){
        Helicopter helicopter = (Helicopter)this.element;
        return helicopter.hasVerticalMissiles();
    }

    private boolean hasHorizontalMissiles(){
        Helicopter helicopter = (Helicopter)this.element;
        return helicopter.hasHorizontalMissiles();
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
