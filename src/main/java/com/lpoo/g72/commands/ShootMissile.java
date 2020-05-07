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
        Missile missile = this.helicopter.shoot();
        if(missile != null){
            missile.setPosition(this.helicopter.getPosition().right().right());
            missile.activate();
        }
    }
}
