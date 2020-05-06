package com.lpoo.g72.creator;

import com.lpoo.g72.gui.Scene;
import com.lpoo.g72.gui.visualElement.VisualMonster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LisbonSceneCreator extends SceneCreator {

    @Override
    public Scene createScene(int width, int height) {

        List<VisualMonster> visualMonsters = new ArrayList<>();

        visualMonsters.add(new VisualMonster());

        this.scene = new Scene(width, height, visualMonsters,3);

        // TODO: Create some elements like buildings, monsters, etc

        this.scene.setBuildings(this.generateBuildings(width,height, new Random(2),2));

        return this.scene;
    }

}

