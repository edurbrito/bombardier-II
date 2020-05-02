package com.lpoo.g72.model.element;

import com.lpoo.g72.model.Position;

public abstract class Element{
    private Position position;

    public Element(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}