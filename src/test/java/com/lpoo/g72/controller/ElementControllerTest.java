package com.lpoo.g72.controller;

import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.gui.visualElement.VisualElement;
import com.lpoo.g72.gui.visualElement.VisualElementStub;
import com.lpoo.g72.gui.visualElement.VisualHelicopter;
import com.lpoo.g72.gui.visualElement.VisualMonster;
import com.lpoo.g72.model.ElementStub;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Element;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Monster;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Instant;

import static org.junit.Assert.*;

public class ElementControllerTest {

    private ElementController elementController;
    private Element element;
    private VisualElement visualElement;
    private HelicopterController helicopterController;
    private MonsterController monsterController;

    @Before
    public void init(){

        this.visualElement = Mockito.spy(new VisualElementStub(new char[]{'a','b','c'},new String[]{"A","B","C"}));
        this.element = Mockito.spy(new ElementStub(new Position(0,1)));

        this.elementController = new ElementController(visualElement,element) {
            @Override
            protected void move() {

            }
        };
    }

    @Test
    public void testInit(){
        assertNotNull(this.elementController.getElement());
        assertNotNull(this.elementController.getVisualElement());
        assertNotNull(this.elementController.commandInvoker);

        assertEquals(0,this.elementController.getElementX());
        assertEquals(1,this.elementController.getElementY());

        Mockito.verify(this.element,Mockito.times(2)).getPosition();
    }

    @Test
    public void testHelicopterController(){
        VisualHelicopter visualHelicopter = Mockito.spy(new VisualHelicopter());
        Helicopter helicopter = Mockito.spy(new Helicopter(new Position(0,1),10,20));

        this.helicopterController = Mockito.spy(new HelicopterController(visualHelicopter,helicopter,10,20));

        assertEquals(10,this.helicopterController.maxWidth);
        assertEquals(this.helicopterController.getElementY(),this.helicopterController.altitude);

        Instant lastInstant = this.helicopterController.lastForwardMove;
        assertNotNull(this.helicopterController.lastForwardMove);
        assertEquals(0.1*Math.pow(10,9),this.helicopterController.deltaTime,10);

        this.helicopterController.run(Gui.Key.DOWN);

        Mockito.verify(this.helicopterController,Mockito.times(1)).move();

        if(lastInstant != this.helicopterController.lastForwardMove){
            Mockito.verify(this.helicopterController,Mockito.times(1)).getElementX();

            Mockito.verify(visualHelicopter,Mockito.times(1)).animation();
            assertNotEquals(lastInstant,this.helicopterController.lastForwardMove);
        }

        Mockito.clearInvocations(this.helicopterController);

        this.helicopterController.run(Gui.Key.UP);

        Mockito.verify(this.helicopterController,Mockito.times(1)).move();

        if(lastInstant != this.helicopterController.lastForwardMove){
            Mockito.verify(this.helicopterController,Mockito.times(1)).getElementX();

            Mockito.verify(visualHelicopter,Mockito.times(1)).animation();
            assertNotEquals(lastInstant,this.helicopterController.lastForwardMove);
        }
    }

    @Test
    public void testMonsterController(){
        VisualMonster visualMonster = Mockito.spy(new VisualMonster());
        Monster monster = Mockito.spy(new Monster(new Position(0,1)));

        this.monsterController = Mockito.spy(new MonsterController(visualMonster,monster,10));

        assertEquals(10,this.monsterController.maxWidth);
        assertEquals(this.monsterController.getElementY(),this.monsterController.altitude);

        Instant lastInstant = this.monsterController.lastForwardMove;
        assertNotNull(this.monsterController.lastForwardMove);
        assertEquals(0.1*Math.pow(10,9),this.monsterController.deltaTime,10);

        this.monsterController.move();

        Mockito.verify(this.monsterController,Mockito.times(1)).move();

        if(lastInstant != this.monsterController.lastForwardMove){
            Mockito.verify(this.monsterController,Mockito.times(1)).getElementX();

            Mockito.verify(visualMonster,Mockito.times(1)).animation();
            assertNotEquals(lastInstant,this.monsterController.lastForwardMove);
        }

        VisualHelicopter visualHelicopter = Mockito.spy(new VisualHelicopter());
        Helicopter helicopter = Mockito.spy(new Helicopter(new Position(0,1),4,5));

        this.helicopterController = Mockito.spy(new HelicopterController(visualHelicopter,helicopter,10,20));

        this.helicopterController.addObserver(this.monsterController);

        this.helicopterController.notifyObservers();

        Mockito.verify(this.monsterController,Mockito.times(1)).update(this.helicopterController.altitude);

        Mockito.verify(monster,Mockito.times(1)).setPosition(Mockito.any());

        this.helicopterController.removeObserver(this.monsterController);

    }
}
