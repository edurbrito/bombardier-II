package com.lpoo.g72.scene;

public class Element{
    private Position position;
    private String str;
    private String color;

    public String getStr() {
        return str;
    }

    public String getColor() {
        return color;
    }

    public Element(Position position, String str, String color) {
        this.position = position;
        this.color = color;
        this.str = str;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}