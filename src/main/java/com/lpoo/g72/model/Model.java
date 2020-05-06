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
        this.helicopter = new Helicopter(new Position(0,1), 50, 3);
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }

    public void addMonster(Monster monster){ this.monsters.add(monster);}

}