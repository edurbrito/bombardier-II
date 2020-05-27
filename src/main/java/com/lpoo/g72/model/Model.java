package com.lpoo.g72.model;

import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Monster;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Monster> monsters;
    private Helicopter helicopter;

    public Model() {
        this.monsters = new ArrayList<>();
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }

    public void setHelicopter(Helicopter helicopter) {
        this.helicopter = helicopter;
    }

    public void addMonster(Monster monster) {
        this.monsters.add(monster);
    }

    public void deleteMonsters() {
        this.monsters.clear();
    }

    public void reset(Helicopter helicopter) {
        this.helicopter = helicopter;
        this.deleteMonsters();
    }
}