package com.lpoo.g72.states.bombDropStates;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.gui.VisualHelicopter;
import com.lpoo.g72.states.BombDropState;

import java.time.Instant;

public class BombDropEnd extends BombDropState {

    public BombDropEnd(VisualHelicopter visualHelicopter) {
        super(visualHelicopter);
    }

    @Override
    public void bombDropAction(Gui.Key key){
        visualHelicopter.setLastBombDropEnd(Instant.now());
        visualHelicopter.setWing(VisualHelicopter.WING.NORMAL);
        visualHelicopter.setBombDropState(new CantDropBomb(visualHelicopter));
    }
}
