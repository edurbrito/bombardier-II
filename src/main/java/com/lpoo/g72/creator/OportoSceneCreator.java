package com.lpoo.g72.creator;

import com.lpoo.g72.gui.Scene;
import com.lpoo.g72.gui.visualElement.VisualMonster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OportoSceneCreator extends SceneCreator {

    @Override
    public Scene createScene(int width, int height) {

        List<VisualMonster> visualMonsters = new ArrayList<>();

        for(int i = 0; i < 2; i++){
            visualMonsters.add(new VisualMonster());
        }

        this.scene = new Scene(width, height, "OPorto", visualMonsters);

        // TODO: Create some elements like buildings, monsters, etc

        this.scene.setBuildings(this.generateBuildings(width,height, new Random(1),2));

        return this.scene;
    }

}