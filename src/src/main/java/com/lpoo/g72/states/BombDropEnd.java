package com.lpoo.g72.states;
import com.lpoo.g72.gui.VisualHelicopter;

import java.time.Instant;

public class BombDropEnd extends BombDropState {

    public BombDropEnd(VisualHelicopter visualHelicopter) {
        super(visualHelicopter);

    }

    @Override
    public void bombDropAction(){
        visualHelicopter.setLastBombDropEnd(Instant.now());
        visualHelicopter.setBombDropState(new CantDropBomb(visualHelicopter));
    }
}
