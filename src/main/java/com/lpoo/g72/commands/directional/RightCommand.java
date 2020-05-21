package com.lpoo.g72.commands.directional;

import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Element;

public class RightCommand extends DirectionalCommand {
    public RightCommand(Element element) {
        super(element);
    }

    @Override
    public void execute() {
        Position position = this.element.getPosition().right();
        this.element.setPosition(position);
    }

}
