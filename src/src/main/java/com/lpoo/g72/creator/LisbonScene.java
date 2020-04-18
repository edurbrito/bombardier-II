package com.lpoo.g72.creator;

import com.lpoo.g72.scene.Scene;

public class LisbonScene extends SceneCreator {

    @Override
    public Scene createScene(int width, int height) {

        // TODO: Create some elements like buildings, monsters, etc

        return new Scene(width,height);
    }

    @Override
    public char[][] generateBuildings(int width, int height) {
        return new char[0][];
    }
}
