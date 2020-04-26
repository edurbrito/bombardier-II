package com.lpoo.g72.states;
import com.lpoo.g72.scene.visualElement.VisualHelicopter;

public abstract class BombDropState {
    protected VisualHelicopter visualHelicopter;

    public BombDropState(VisualHelicopter visualHelicopter) {
        this.visualHelicopter = visualHelicopter;
    }

    public abstract void bombDropAction();
}
