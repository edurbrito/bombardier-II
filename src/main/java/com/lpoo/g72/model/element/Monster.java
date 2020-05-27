package com.lpoo.g72.model.element;

import com.lpoo.g72.model.Position;

public class Monster extends Element {
    private boolean alive;

    public Monster(Position position) {
        super(position);
        this.alive = true;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void kill() {
        this.alive = false;
    }

    public void revive() {
        this.alive = true;
    }
}
