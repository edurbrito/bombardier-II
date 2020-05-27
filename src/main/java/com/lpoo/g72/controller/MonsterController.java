package com.lpoo.g72.controller;

import com.lpoo.g72.commands.directional.LeftCommand;
import com.lpoo.g72.gui.visualElement.VisualElement;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Monster;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class MonsterController extends ElementController implements Observer{

    private VisualElement visualMonster;
    private Monster monster;

    public MonsterController(VisualElement visualMonster, Monster monster, int maxWidth) {
        super(maxWidth, monster.getPosition().getY(),0.12 * Math.pow(10,9));

        this.monster = monster;
        this.visualMonster = visualMonster;
    }

    @Override
    protected void move() {
        Instant current = Instant.now();
        Duration timePassed = Duration.between(this.lastForwardMove , current);

        if(timePassed.getNano() >= this.deltaTime){

            this.commandInvoker.addCommand(new LeftCommand(this.monster));

            this.visualMonster.animation();

            this.lastForwardMove = current;
        }
    }

    @Override
    public void update(int info) {

        this.altitude = info + new Random().nextInt(3) - 1;

        this.monster.setPosition(new Position(this.maxWidth + new Random().nextInt(30),this.altitude));

        this.monster.revive();
    }
}
