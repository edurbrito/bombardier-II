package com.lpoo.g72.scene;

import com.lpoo.g72.scene.visualElement.VisualHelicopter;
import com.lpoo.g72.scene.visualElement.VisualMonster;

import java.util.ArrayList;
import java.util.List;

public class Scene{
    private int width;
    private int height;
    private char[][] buildings;
    private VisualHelicopter helicopter;
    private List<VisualMonster> monsters;

    public Scene(int width, int height){
        this.width = width;
        this.height = height;
        this.monsters = new ArrayList<>();
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

    public VisualHelicopter getHelicopter() {
        return this.helicopter;
    }

    public void setHelicopter(VisualHelicopter helicopter) {
        this.helicopter = helicopter;
    }

    public List<VisualMonster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<VisualMonster> monsters) {
        this.monsters = monsters;
    }

    public void addMonster(VisualMonster monster){
        this.monsters.add(monster);
    }
}
