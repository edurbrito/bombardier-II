package com.lpoo.g72.commands.directional;

import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.element.Element;

public class RightCommand extends DirectionalCommand {
    public RightCommand(Element element) {
        super(element);
    }

    @Override
    public void execute() {
        Position position = element.getPosition().right();
        element.setPosition(position);
    }

}
