package com.lpoo.g72.commands.directional;

import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Element;

public class LeftCommand extends DirectionalCommand {

    public LeftCommand(Element element) {
        super(element);
    }

    @Override
    public void execute() {
        Position position = this.element.getPosition().left();
        this.element.setPosition(position);
    }
}
