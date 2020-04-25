package com.lpoo.g72.scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scene{
    private int width;
    private int height;
    private char[][] buildings;
    private Helicopter helicopter;

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

    public char[][] getBuildings() {
        return buildings;
    }

    public void setBuildings(char[][] buildings) {
        this.buildings = buildings;
    }

    public Helicopter getHelicopter() {
        return this.helicopter;
    }

    public void setHelicopter(Helicopter helicopter) {
        this.helicopter = helicopter;
    }
}
