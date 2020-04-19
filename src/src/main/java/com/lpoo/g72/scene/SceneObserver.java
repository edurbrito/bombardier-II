package com.lpoo.g72.scene;

import java.io.IOException;

public interface SceneObserver {
    void sceneChanged(Scene scene) throws IOException, InterruptedException;
}