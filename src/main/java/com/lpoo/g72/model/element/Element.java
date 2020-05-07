package com.lpoo.g72.model.element;

import com.lpoo.g72.model.Position;

public abstract class Element{
    private Position position;

    public Element(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public int getX() {
        return this.position.getX();
    }

    public int getY() {
        return this.position.getY();
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}