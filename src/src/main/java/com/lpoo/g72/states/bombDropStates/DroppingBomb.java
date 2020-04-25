package com.lpoo.g72.states.bombDropStates;

import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.gui.VisualHelicopter;
import com.lpoo.g72.states.BombDropState;

import java.time.Duration;
import java.time.Instant;

public class DroppingBomb extends BombDropState {

    public DroppingBomb(VisualHelicopter visualHelicopter) {
        super(visualHelicopter);
    }

    @Override
    public void bombDropAction(Gui.Key key){
        Duration timePassed = Duration.between(visualHelicopter.getLastBombDropStart(), Instant.now());

        if(timePassed.getNano() >= visualHelicopter.getLaunchingTime())
            visualHelicopter.setBombDropState(new BombDropEnd(visualHelicopter));
    }
}
