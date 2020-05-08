package com.lpoo.g72.model.element;

import com.lpoo.g72.model.Position;

public class Monster extends Element {
    private boolean alive;
    private boolean resting;

    public Monster(Position position) {
        super(position);
        this.alive = true;
        this.resting = false;
    }

    public boolean isAlive(){
        return this.alive;
    }

    public boolean isResting(){
        return this.resting;
    }

    public void rest(){
        this.resting = true;
    }

    public void kill(){
        this.alive = false;
    }

    public void revive(){
        this.alive = true;
        this.resting = false;
    }
}
