package com.lpoo.g72.model;

import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Monster;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ModelTest {
    Model model;

    @Before
    public void initModel(){
        this.model = new Model();
    }

    @Test
    public void testInit(){
        model.setHelicopter(new Helicopter(new Position(0,1),5,6));
        assertNotNull(this.model.getHelicopter());
        assertEquals(new Position(0,1),this.model.getHelicopter().getPosition());
        assertEquals(5, this.model.getHelicopter().unusedVerticalMissiles());
        assertEquals(6, this.model.getHelicopter().unusedHorizontalMissiles());
        assertEquals(0, this.model.getMonsters().size());
    }

    @Test
    public void testAddMonsters(){
        this.model.addMonster(new Monster(new Position(12,45)));
        this.model.addMonster(new Monster(new Position(14,50)));
        this.model.addMonster(new Monster(new Position(16,55)));
        this.model.addMonster(new Monster(new Position(18,60)));
        this.model.addMonster(new Monster(new Position(20,65)));

        assertEquals(5, this.model.getMonsters().size());

        this.model.reset(new Helicopter(new Position(15,21),3,4));
        assertNotNull(this.model.getHelicopter());
        assertEquals(new Position(15,21),this.model.getHelicopter().getPosition());
        assertEquals(3, this.model.getHelicopter().unusedVerticalMissiles());
        assertEquals(4, this.model.getHelicopter().unusedHorizontalMissiles());
        assertEquals(0, this.model.getMonsters().size());
    }
}
