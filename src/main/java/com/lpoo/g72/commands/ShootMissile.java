package com.lpoo.g72.commands;

import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Missile;

public class ShootMissile implements Command {
    Helicopter helicopter;

    public ShootMissile(Helicopter helicopter) {
        this.helicopter = helicopter;
    }

    @Override
    public void execute() {
        for(Missile missile : this.helicopter.getInactiveHorizontalMissiles()){
            missile.setPosition(this.helicopter.getPosition().right().right());
            missile.activate();
            return;
        }
    }
}
