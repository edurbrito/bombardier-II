package com.lpoo.g72.scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scene{
    private int width;
    private int height;
    private List<SceneObserver> observers;
    private char[][] buildings;
    private Helicopter helicopter;

    public Helicopter getHelicopter() {
        return helicopter;
    }

    public Scene(int width, int height){
        this.width = width;
        this.height = height;
        this.observers = new ArrayList<>();
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

    public void addObserver(SceneObserver observer) throws IOException {
        observers.add(observer);
    }

    public void notifyObservers() throws IOException, InterruptedException {
        for (SceneObserver observer : observers) {
            observer.sceneChanged(this);
        }
    }

    public void setHelicopter(Helicopter helicopter) {
        this.helicopter = helicopter;
    }
}
