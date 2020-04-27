package com.lpoo.g72.creator;

import com.lpoo.g72.scene.visualElement.VisualHelicopter;
import com.lpoo.g72.scene.element.Helicopter;
import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.Scene;

import java.util.Random;

public class OportoSceneCreator extends SceneCreator {

    @Override
    public Scene createScene(int width, int height) {

        this.scene = new Scene(width,height);

        this.scene.setHelicopter(new VisualHelicopter(new Helicopter(new Position(0,1))));

        // TODO: Create some elements like buildings, monsters, etc

        this.scene.setBuildings(this.generateBuildings(scene.getWidth(),scene.getHeight(), new Random(1),1));

        return this.scene;
    }

}
