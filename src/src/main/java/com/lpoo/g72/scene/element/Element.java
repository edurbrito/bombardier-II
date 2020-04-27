package com.lpoo.g72.scene.element;

import com.lpoo.g72.scene.Position;

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