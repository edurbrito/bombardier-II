package com.lpoo.g72.controller;

import com.lpoo.g72.creator.LisbonSceneCreator;
import com.lpoo.g72.gui.Scene;
import com.lpoo.g72.model.Model;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Missile;
import com.lpoo.g72.model.element.Monster;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollisionControllerTest {

    CollisionController collisionController;
    Model model;
    Scene scene;

    @Before
    public void init() {
        this.model = new Model();
        this.collisionController = new CollisionController(model);
        this.scene = new LisbonSceneCreator().createScene(100, 50);
        this.collisionController.initScene(this.scene);
    }

    @Test
    public void testHorizontalCollision() {

        model.setHelicopter(new Helicopter(new Position(30, 30), 1, 2));
        model.addMonster(new Monster(new Position(30, 30)));

        assertEquals(2, model.getHelicopter().getLives());

        int points = this.collisionController.horizontalCollisions();

        assertEquals(0, points);
        assertEquals(1, model.getHelicopter().getLives());
        assertFalse(model.getMonsters().get(model.getMonsters().size() - 1).isAlive());

        Missile missile = model.getHelicopter().shoot();
        missile.setPosition(new Position(30, 30));
        missile.activate();
        model.getMonsters().get(model.getMonsters().size() - 1).revive();

        points = this.collisionController.horizontalCollisions();
        assertEquals(1, points);
        assertFalse(model.getMonsters().get(model.getMonsters().size() - 1).isAlive());
        assertTrue(missile.hasExploded());

    }

    @Test
    public void testVerticalCollision() {

        model.setHelicopter(new Helicopter(new Position(30, 30), 2, 2));

        assertEquals(2, model.getHelicopter().getLives());

        Missile missile = model.getHelicopter().drop();

        missile.activate();

        int blocks = collisionController.blocksDestroyed();

        assertEquals(3, blocks);

        missile.setPosition(new Position(30, 40));

        blocks = collisionController.blocksDestroyed();

        assertEquals(3, blocks);

        missile.setPosition(new Position(30, 50));

        blocks = collisionController.blocksDestroyed();

        assertEquals(0, blocks);

        missile.setPosition(new Position(30, 8));

        blocks = collisionController.blocksDestroyed();

        assertEquals(1, blocks);

        missile.setPosition(new Position(30, 10));

        blocks = collisionController.blocksDestroyed();

        assertEquals(2, blocks);
    }

    @Test
    public void testBuildingCollision() {

        model.setHelicopter(new Helicopter(new Position(30, 30), 1, 2));

        assertTrue(collisionController.buildingsCollisions());

        model.setHelicopter(new Helicopter(new Position(30, 10), 1, 2));

        assertTrue(collisionController.buildingsCollisions());

        model.setHelicopter(new Helicopter(new Position(30, 5), 1, 2));

        assertTrue(collisionController.buildingsCollisions());

        model.setHelicopter(new Helicopter(new Position(30, 2), 1, 2));

        assertFalse(collisionController.buildingsCollisions());

        model.setHelicopter(new Helicopter(new Position(20, 2), 1, 2));

        assertFalse(collisionController.buildingsCollisions());
    }
}
