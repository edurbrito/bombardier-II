package com.lpoo.g72.controller;

import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.gui.visualElement.VisualElement;
import com.lpoo.g72.gui.visualElement.VisualElementStub;
import com.lpoo.g72.gui.visualElement.VisualHelicopter;
import com.lpoo.g72.gui.visualElement.visualMonsters.VisualPteranodon;
import com.lpoo.g72.model.ElementStub;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Element;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Monster;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Instant;

import static org.junit.Assert.*;

public class ElementControllerTest {

    private HelicopterController helicopterController;

    @Test
    public void testHelicopterController(){
        VisualHelicopter visualHelicopter = Mockito.spy(new VisualHelicopter());
        Helicopter helicopter = Mockito.spy(new Helicopter(new Position(0,1),10,20));

        this.helicopterController = Mockito.spy(new HelicopterController(visualHelicopter,helicopter,10,20));

        assertEquals(10,this.helicopterController.maxWidth);
        assertEquals(helicopter.getY(),this.helicopterController.altitude);

        Instant lastInstant = this.helicopterController.lastForwardMove;
        assertNotNull(this.helicopterController.lastForwardMove);
        assertEquals(10, this.helicopterController.getMaxWidth());
        assertEquals(1, this.helicopterController.getAltitude());
        assertEquals(0.12 * Math.pow(10,9),this.helicopterController.getDeltaTime(),10);

        this.helicopterController.run(Gui.Key.DOWN);

        Mockito.verify(this.helicopterController,Mockito.times(1)).move();

        if(lastInstant != this.helicopterController.lastForwardMove){
            Mockito.verify(helicopter,Mockito.times(1)).getX();

            Mockito.verify(visualHelicopter,Mockito.times(1)).animation();
            assertNotEquals(lastInstant,this.helicopterController.lastForwardMove);
        }

        Mockito.clearInvocations(this.helicopterController);

        this.helicopterController.run(Gui.Key.UP);

        Mockito.verify(this.helicopterController,Mockito.times(1)).move();

        if(lastInstant != this.helicopterController.lastForwardMove){
            Mockito.verify(helicopter,Mockito.times(1)).getX();

            Mockito.verify(visualHelicopter,Mockito.times(1)).animation();
            assertNotEquals(lastInstant,this.helicopterController.lastForwardMove);
        }
    }

    @Test
    public void testMonsterController(){
        VisualPteranodon visualPteranodon = Mockito.spy(new VisualPteranodon());
        Monster monster = Mockito.spy(new Monster(new Position(0,1)));

        MonsterController monsterController = Mockito.spy(new MonsterController(visualPteranodon, monster, 10));

        assertEquals(10, monsterController.maxWidth);
        assertEquals(monster.getY(), monsterController.altitude);

        Instant lastInstant = monsterController.lastForwardMove;
        assertNotNull(monsterController.lastForwardMove);
        assertEquals(0.12*Math.pow(10,9), monsterController.deltaTime,10);

        monsterController.move();

        Mockito.verify(monsterController,Mockito.times(1)).move();

        if(lastInstant != monsterController.lastForwardMove){
            Mockito.verify(monster.getY(),Mockito.times(1));

            Mockito.verify(visualPteranodon,Mockito.times(1)).animation();
            assertNotEquals(lastInstant, monsterController.lastForwardMove);
        }

        VisualHelicopter visualHelicopter = Mockito.spy(new VisualHelicopter());
        Helicopter helicopter = Mockito.spy(new Helicopter(new Position(0,1),4,5));

        this.helicopterController = Mockito.spy(new HelicopterController(visualHelicopter,helicopter,10,20));

        this.helicopterController.addObserver(monsterController);

        this.helicopterController.notifyObservers();

        Mockito.verify(monsterController,Mockito.times(1)).update(this.helicopterController.altitude);

        assertEquals(this.helicopterController.altitude,monsterController.altitude,4);
        assertNotNull(monsterController.monster.getPosition());
        Mockito.verify(monster, Mockito.times(1)).setPosition(Mockito.any());
        Mockito.verify(monster, Mockito.times(1)).revive();

        Mockito.verify(monster,Mockito.times(1)).setPosition(Mockito.any());

        this.helicopterController.removeObserver(monsterController);

    }
}
