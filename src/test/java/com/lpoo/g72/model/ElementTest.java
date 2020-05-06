package com.lpoo.g72.model;

import com.lpoo.g72.model.element.Element;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Monster;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElementTest {
    @Test
    public void elementTest(){
        Element element = new Helicopter(new Position(15,16),4,5);

        assertEquals(true, element.getPosition().equals(new Position(15,16)));

        element.setPosition(new Position(15,16));
        assertEquals(true, element.getPosition().equals(new Position(15,16)));

        element = new Monster(new Position(31,22));

        assertEquals(true, element.getPosition().equals(new Position(31,22)));

        element.setPosition(new Position(19,95));
        assertEquals(true, element.getPosition().equals(new Position(19,95)));
    }
}
