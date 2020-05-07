package com.lpoo.g72.model.element;

import com.lpoo.g72.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Helicopter extends Element{
    private List<Missile> verticalMissiles;
    private List<Missile> horizontalMissiles;

    public Helicopter(Position position, int verticalMissiles, int horizontalMissiles) {
        super(position);
        this.initMissiles(verticalMissiles, horizontalMissiles);
    }

    private void initMissiles(int verticalMissiles, int horizontalMissiles) {

        this.verticalMissiles = new ArrayList<>();
        this.horizontalMissiles = new ArrayList<>();

        for(int i = 0; i < verticalMissiles; i++){
            this.verticalMissiles.add(new Missile(this.getPosition()));
        }

        for(int i = 0; i < horizontalMissiles; i++){
            this.horizontalMissiles.add(new Missile(this.getPosition()));
        }
    }

    public void resetMissiles(){
        for(Missile missile : this.verticalMissiles){
            missile.deactivate();
        }
        for(Missile missile : this.horizontalMissiles){
            missile.deactivate();
        }
    }

    public List<Missile> getVerticalMissiles() {
        List<Missile> activeMissiles = new ArrayList<>();

        for(Missile missile : this.verticalMissiles){
            if(missile.isActive() && !missile.hasExploded()){
                activeMissiles.add(missile);
            }
        }

        return activeMissiles;
    }

    public List<Missile> getHorizontalMissiles() {
        List<Missile> activeMissiles = new ArrayList<>();

        for(Missile missile : this.horizontalMissiles){
            if(missile.isActive() && !missile.hasExploded()){
                activeMissiles.add(missile);
            }
        }

        return activeMissiles;
    }

    public List<Missile> getInactiveVerticalMissiles() {
        List<Missile> inactiveMissiles = new ArrayList<>();

        for(Missile missile : this.verticalMissiles){
            if(!missile.isActive()){
                inactiveMissiles.add(missile);
            }
        }

        return inactiveMissiles;
    }

    public List<Missile> getInactiveHorizontalMissiles() {
        List<Missile> inactiveMissiles = new ArrayList<>();

        for(Missile missile : this.horizontalMissiles){
            if(!missile.isActive()){
                inactiveMissiles.add(missile);
            }
        }

        return inactiveMissiles;
    }
}