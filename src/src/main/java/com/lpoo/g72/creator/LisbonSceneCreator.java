package com.lpoo.g72.creator;

import com.lpoo.g72.scene.visualElement.VisualHelicopter;
import com.lpoo.g72.scene.visualElement.VisualMonster;
import com.lpoo.g72.scene.element.Helicopter;
import com.lpoo.g72.scene.element.Monster;
import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.Scene;

import java.util.Random;

public class LisbonSceneCreator extends SceneCreator {

    @Override
    public Scene createScene(int width, int height) {

        this.scene = new Scene(width,height);

        this.scene.setHelicopter(new VisualHelicopter(new Helicopter(new Position(0,1))));

        for(int i = 0; i < 3; i++){
            this.scene.addMonster(new VisualMonster(new Monster(new Position(this.scene.getWidth() + new Random().nextInt(20),i*2))));
        }

        // TODO: Create some elements like buildings, monsters, etc

        this.scene.setBuildings(this.generateBuildings(scene.getWidth(),scene.getHeight(), new Random(2),2));

        return this.scene;
    }

}
