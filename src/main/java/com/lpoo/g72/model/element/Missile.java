package com.lpoo.g72.model.element;

import com.lpoo.g72.model.Position;

public class Missile extends Element {
    private boolean active;
    private boolean exploded;

    public Missile(Position position) {
        super(position);
        this.active = false;
        this.exploded = false;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean hasExploded() {
        return this.exploded;
    }

    public void activate() {
        this.active = true;
        this.exploded = false;
    }

    public void setExploded() {
        this.exploded = true;
    }

    public void deactivate() {
        this.active = false;
        this.exploded = false;
    }
}
