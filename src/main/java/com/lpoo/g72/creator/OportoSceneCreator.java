package com.lpoo.g72.creator;

import com.lpoo.g72.gui.Scene;
import com.lpoo.g72.gui.visualElement.VisualElement;
import com.lpoo.g72.gui.visualElement.visualMonsters.VisualCretaceous;
import com.lpoo.g72.gui.visualElement.visualMonsters.VisualPteranodon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OportoSceneCreator extends SceneCreator {

    @Override
    public Scene createScene(int width, int height) {

        List<VisualElement> visualMonsters = new ArrayList<>();

        visualMonsters.add(new VisualCretaceous());
        visualMonsters.add(new VisualPteranodon());

        this.scene = new Scene(width, height, "OPorto", visualMonsters);

        this.scene.setBuildings(this.generateBuildings(width, height, new Random(1), 2));

        return this.scene;
    }

}