package com.lpoo.g72.controller;

import com.lpoo.g72.commands.directional.LeftCommand;
import com.lpoo.g72.gui.visualElement.VisualElement;
import com.lpoo.g72.gui.visualElement.VisualMonster;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.gui.Scene;
import com.lpoo.g72.model.element.Monster;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class MonsterController extends ElementController implements Observer{

    public MonsterController(Scene scene, VisualMonster visualMonster, Monster monster) {
        super(visualMonster, monster);

        this.maxWidth = scene.getWidth() -1;
        this.altitude = this.getElementY();

        this.lastForwardMove = Instant.now();
        this.velocity = 0.2 * Math.pow(10,9);
    }

    @Override
    protected void move() {
        Instant current = Instant.now();
        Duration timePassed = Duration.between(this.lastForwardMove , current);

        if(timePassed.getNano() >= velocity){

            if(this.getElementX() == 0)
                this.element.setPosition(new Position(this.maxWidth + new Random().nextInt(30),this.altitude));

            commandInvoker.addCommand(new LeftCommand(this.element));

            this.visualElement.animation();

            this.lastForwardMove = current;
        }
    }

    @Override
    public void update(int info) {
        this.altitude = info + new Random().nextInt(6) - 2;
        this.element.setPosition(new Position(this.maxWidth + new Random().nextInt(30),this.altitude));
    }
}
