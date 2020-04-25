package com.lpoo.g72.states.bombDropStates;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.gui.VisualHelicopter;
import com.lpoo.g72.states.BombDropState;

import java.time.Instant;

public class BombDropStart extends BombDropState {

    public BombDropStart(VisualHelicopter visualHelicopter) {
        super(visualHelicopter);
    }

    @Override
    public void bombDropAction(Gui.Key key){
        visualHelicopter.setLastBombDropStart(Instant.now());
        visualHelicopter.setWing(VisualHelicopter.WING.LAUNCHING);
        visualHelicopter.setBombDropState(new DroppingBomb(visualHelicopter));
    }
}
