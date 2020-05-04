package com.lpoo.g72.commands;

import com.lpoo.g72.commands.Command;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Missile;

public class ShootMissile implements Command {
    Helicopter helicopter;

    public ShootMissile(Helicopter helicopter) {
        this.helicopter = helicopter;
    }

    @Override
    public void execute() {
        Position position = new Position(this.helicopter.getPosition().getX() + 3, this.helicopter.getPosition().getY());
        Missile missile = new Missile(position);
        missile.activate();
        this.helicopter.addHorizontalMissile(missile);
    }
}
