package com.lpoo.g72.scene;

public class Scene {
    private int width;
    private int height;
    private char[][] buildings;

    public Scene(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setBuildings(char[][] buildings) {
        this.buildings = buildings;
    }

    public char[][] getBuildings() {
        return buildings;
    }
}
