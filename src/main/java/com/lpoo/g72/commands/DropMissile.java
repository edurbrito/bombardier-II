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
        Missile missile = new Missile(this.helicopter.getPosition());
        missile.activate();
        this.helicopter.addVerticalMissile(missile);
    }
}
