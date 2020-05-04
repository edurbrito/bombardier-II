package com.lpoo.g72.model.element;

import com.lpoo.g72.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Helicopter extends Element{

    private List<Missile> horizontalMissiles;
    private List<Missile> verticalMissiles;

    private int availableMissiles[];
    private int maxMissiles[];

    public Helicopter(Position position, int verticalMissiles, int horizontalMissiles) {
        super(position);
        initMissiles(verticalMissiles, horizontalMissiles);
    }

    private void initMissiles(int verticalMissiles, int horizontalMissiles) {
        this.availableMissiles =  new int[2];
        this.maxMissiles =  new int[2];
        this.horizontalMissiles = new ArrayList<>();
        this.verticalMissiles = new ArrayList<>();

        this.availableMissiles[0] = verticalMissiles;
        this.maxMissiles[0] = verticalMissiles;
        this.availableMissiles[1] = horizontalMissiles;
        this.maxMissiles[1] = horizontalMissiles;
    }

    public List<Missile> getHorizontalMissiles() {
        List<Missile> activeMissiles = new ArrayList<>();

        for(int i = 0; i < horizontalMissiles.size(); i++){
            if(horizontalMissiles.get(i).isActive()){
                activeMissiles.add(horizontalMissiles.get(i));
            }
        }

        return activeMissiles;
    }

    public List<Missile> getVerticalMissiles() {
        List<Missile> activeMissiles = new ArrayList<>();

        for(int i = 0; i < verticalMissiles.size(); i++){
            if(verticalMissiles.get(i).isActive()){
                activeMissiles.add(verticalMissiles.get(i));
            }
        }

        return activeMissiles;
    }

    public void resetMissiles(){
        this.availableMissiles[0] = this.maxMissiles[0];
        this.availableMissiles[1] = this.maxMissiles[1];
    }

    public boolean hasVerticalMissiles(){
        return availableMissiles[0] > 0;
    }

    public boolean hasHorizontalMissiles(){
        return availableMissiles[1] > 0;
    }

    public void addVerticalMissile(Missile missile){
        this.availableMissiles[0] --;
        this.verticalMissiles.add(missile);
    }

    public void addHorizontalMissile(Missile missile){
        this.availableMissiles[1] --;
        this.horizontalMissiles.add(missile);
    }
}