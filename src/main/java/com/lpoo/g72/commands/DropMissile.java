package com.lpoo.g72.commands;

import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Missile;

public class DropMissile implements Command{
    Helicopter helicopter;

    public DropMissile(Helicopter helicopter) {
        this.helicopter = helicopter;
    }

    @Override
    public void execute() {
        for(Missile missile : this.helicopter.getInactiveVerticalMissiles()){
            missile.setPosition(this.helicopter.getPosition().down());
            missile.activate();
            return;
        }
    }
}
