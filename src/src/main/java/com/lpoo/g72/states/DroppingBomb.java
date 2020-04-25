package com.lpoo.g72.states;

import com.lpoo.g72.gui.VisualHelicopter;

import java.time.Duration;
import java.time.Instant;

public class DroppingBomb extends BombDropState {

    public DroppingBomb(VisualHelicopter visualHelicopter) {
        super(visualHelicopter);
    }

    @Override
    public void bombDropAction(){
        Duration timePassed = Duration.between(visualHelicopter.getLastBombDropStart(), Instant.now());

        if(timePassed.getNano() >= visualHelicopter.getLaunchingTime())
            visualHelicopter.setBombDropState(new BombDropEnd(visualHelicopter));
    }
}
