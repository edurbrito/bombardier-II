package com.lpoo.g72.commands;

import com.lpoo.g72.model.element.Helicopter;

public class ShootMissile implements Command {
    Helicopter helicopter;

    public ShootMissile(Helicopter helicopter) {
        this.helicopter = helicopter;
    }

    @Override
    public void execute() {
        this.helicopter.shootMissile();
    }
}
