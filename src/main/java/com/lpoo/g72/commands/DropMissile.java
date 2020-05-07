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
        Missile missile = this.helicopter.drop();
        if(missile != null){
            missile.setPosition(this.helicopter.getPosition().down());
            missile.activate();
        }
    }
}
