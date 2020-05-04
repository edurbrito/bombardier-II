package com.lpoo.g72.model.element;

import com.lpoo.g72.model.Position;

public class Missile extends Element{
    private boolean active;

    public Missile(Position position) {
        super(position);
        active = false;
    }

    public boolean isActive(){
        return this.active;
    }

    public void activate() {
        this.active = true;
    }
    public void deactivate() {
        this.active = false;
    }
}
