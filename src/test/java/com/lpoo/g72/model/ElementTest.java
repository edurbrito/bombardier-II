package com.lpoo.g72.model;

import com.lpoo.g72.model.element.Element;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Monster;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElementTest {
    @Test
    public void elementTest() {
        Element element = new Helicopter(new Position(15, 16), 4, 5);

        assertEquals(true, element.getPosition().equals(new Position(15, 16)));
        assertEquals(15, element.getX());
        assertEquals(16, element.getY());

        element.setPosition(new Position(15, 17));
        assertEquals(true, element.getPosition().equals(new Position(15, 17)));
        assertEquals(15, element.getX());
        assertEquals(17, element.getY());

        element = new Monster(new Position(31, 22));

        assertEquals(true, element.getPosition().equals(new Position(31, 22)));
        assertEquals(31, element.getX());
        assertEquals(22, element.getY());

        element.setPosition(new Position(19, 95));
        assertEquals(true, element.getPosition().equals(new Position(19, 95)));

        assertEquals(19, element.getX());
        assertEquals(95, element.getY());

        Helicopter helicopter = new Helicopter(new Position(15, 16), 4, 5);

        for (int i = 0; i < 4; i++) {
            helicopter.drop().activate();
        }

        assertEquals(null, helicopter.drop());

        for (int i = 0; i < 5; i++) {
            helicopter.shoot().activate();
        }

        assertEquals(null, helicopter.shoot());

        assertEquals(2, helicopter.getLives());
        helicopter.loseLife();
        assertEquals(1, helicopter.getLives());
        helicopter.loseLife();
        assertEquals(0, helicopter.getLives());

        Monster monster = new Monster(new Position(1, 4));

        assertEquals(true, monster.isAlive());

        monster.kill();

        assertEquals(false, monster.isAlive());

        monster.revive();

        assertEquals(true, monster.isAlive());
    }
}
