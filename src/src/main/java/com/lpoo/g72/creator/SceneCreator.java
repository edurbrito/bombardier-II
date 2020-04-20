package com.lpoo.g72.creator;
import com.lpoo.g72.scene.Helicopter;
import com.lpoo.g72.scene.Scene;

public abstract class SceneCreator {
    private Scene scene;

    public Scene createScene(int width, int height) {

        // TODO: Create some elements like buildings, monsters, etc
        this.scene = new Scene(width,height);
        this.scene.setBuildings(this.generateBuildings(this.scene.getWidth(),this.scene.getHeight()));
        this.scene.setHelicopter(new Helicopter(0,0));

        return this.scene;
    }

    abstract char[][] generateBuildings(int width, int height);

}