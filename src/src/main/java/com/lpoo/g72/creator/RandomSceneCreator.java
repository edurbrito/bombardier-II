package com.lpoo.g72.creator;

import com.lpoo.g72.gui.visualElement.VisualHelicopter;
import com.lpoo.g72.gui.Scene;
import com.lpoo.g72.gui.visualElement.VisualMonster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSceneCreator extends SceneCreator {

    @Override
    public Scene createScene(int width, int height) {

        //this.scene = new Scene(width, height, new VisualMonster());

        this.scene.setBuildings(this.generateBuildings(width,height, new Random(),3));

        return this.scene;
    }
}
