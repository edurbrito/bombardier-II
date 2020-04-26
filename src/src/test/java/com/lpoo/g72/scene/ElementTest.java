package com.lpoo.g72.scene;

import com.lpoo.g72.scene.element.Element;
import com.lpoo.g72.scene.element.Helicopter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElementTest {
    @Test
    public void elementTest(){
        Element element = new Helicopter(new Position(15,16));

        assertEquals(true, element.getPosition().equals(new Position(15,16)));

        element.setPosition(new Position(15,16));
        assertEquals(true, element.getPosition().equals(new Position(15,16)));
    }
}
