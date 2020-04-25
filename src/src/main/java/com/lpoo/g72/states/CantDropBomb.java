package com.lpoo.g72.states;
import com.lpoo.g72.gui.VisualHelicopter;

import java.time.Duration;
import java.time.Instant;

public class CantDropBomb extends BombDropState {

    public CantDropBomb(VisualHelicopter visualHelicopter) {
        super(visualHelicopter);
    }

    @Override
    public void bombDropAction(){
        Duration timePassed = Duration.between(visualHelicopter.getLastBombDropEnd(), Instant.now());

        if(timePassed.getSeconds() >= visualHelicopter.getUnlaunchableTime())
            visualHelicopter.setBombDropState(new CanDropBomb(visualHelicopter));
    }
}
