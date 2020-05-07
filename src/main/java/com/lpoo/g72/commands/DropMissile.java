package com.lpoo.g72.commands;

import com.lpoo.g72.model.element.Helicopter;

public class DropMissile implements Command{
    Helicopter helicopter;

    public DropMissile(Helicopter helicopter) {
        this.helicopter = helicopter;
    }

    @Override
    public void execute() {
        this.helicopter.dropMissile();
    }
}
