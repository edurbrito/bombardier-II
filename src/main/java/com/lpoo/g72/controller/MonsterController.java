package com.lpoo.g72.controller;

import com.lpoo.g72.commands.directional.LeftCommand;
import com.lpoo.g72.gui.visualElement.VisualMonster;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.gui.Scene;
import com.lpoo.g72.model.element.Missile;
import com.lpoo.g72.model.element.Monster;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class MonsterController extends ElementController implements Observer{

    public MonsterController(VisualMonster visualMonster, Monster monster, int maxWidth) {
        super(visualMonster, monster);

        this.maxWidth = maxWidth;
        this.altitude = this.getElementY();

        this.lastForwardMove = Instant.now();
        this.movingTime = 0.1 * Math.pow(10,9);
    }

    @Override
    protected void move() {
        Instant current = Instant.now();
        Duration timePassed = Duration.between(this.lastForwardMove , current);

        if(timePassed.getNano() >= this.movingTime){

            if(this.getElementX() == 0)
                this.element.setPosition(new Position(this.maxWidth + new Random().nextInt(30),this.altitude));

            this.commandInvoker.addCommand(new LeftCommand(this.element));

            this.visualElement.animation();

            this.lastForwardMove = current;
        }
    }

    @Override
    public void update(int info) {
        this.altitude = info + new Random().nextInt(6) - 2;
        this.element.setPosition(new Position(this.maxWidth + new Random().nextInt(30),this.altitude));

        Monster monster = (Monster) this.element;
        monster.revive();
    }
}
