package com.lpoo.g72.commands.directional;

import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Element;

public class UpCommand extends DirectionalCommand {

    public UpCommand(Element element) {
        super(element);
    }

    @Override
    public void execute() {
        Position position = this.element.getPosition().up();
        this.element.setPosition(position);
    }
}
