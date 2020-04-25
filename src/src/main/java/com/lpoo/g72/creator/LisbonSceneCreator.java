package com.lpoo.g72.creator;

import com.lpoo.g72.scene.Helicopter;
import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.Scene;

import java.util.Random;

public class LisbonSceneCreator extends SceneCreator {

    @Override
    public Scene createScene(int width, int height) {

        this.scene = new Scene(width,height);

        this.scene.setHelicopter(new Helicopter(new Position(0,0)));

        // TODO: Create some elements like buildings, monsters, etc

        this.scene.setBuildings(this.generateBuildings(scene.getWidth(),scene.getHeight(), new Random(2),2));

        return this.scene;
    }

}
