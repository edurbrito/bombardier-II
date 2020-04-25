package com.lpoo.g72.states;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.gui.VisualHelicopter;

public abstract class BombDropState {
    protected VisualHelicopter visualHelicopter;

    public BombDropState(VisualHelicopter visualHelicopter) {
        this.visualHelicopter = visualHelicopter;
    }

    public abstract void bombDropAction();
}
