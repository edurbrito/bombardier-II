package com.lpoo.g72.commands.directional;

import com.lpoo.g72.model.element.Element;
import com.lpoo.g72.model.Position;

public class UpCommand extends DirectionalCommand {

    public UpCommand(Element element) {
        super(element);
    }

    @Override
    public void execute() {
        Position position = element.getPosition().up();
        element.setPosition(position);
    }
}
