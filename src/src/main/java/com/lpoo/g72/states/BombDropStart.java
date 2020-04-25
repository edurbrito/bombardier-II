package com.lpoo.g72.states;
import com.lpoo.g72.gui.VisualHelicopter;

import java.time.Instant;

public class BombDropStart extends BombDropState {

    public BombDropStart(VisualHelicopter visualHelicopter) {
        super(visualHelicopter);
    }

    @Override
    public void bombDropAction(){
        visualHelicopter.setLastBombDropStart(Instant.now());
        visualHelicopter.setWing(VisualHelicopter.WING.LAUNCHING);
        visualHelicopter.setBombDropState(new DroppingBomb(visualHelicopter));
    }
}
