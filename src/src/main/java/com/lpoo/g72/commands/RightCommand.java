package com.lpoo.g72.commands;

import com.lpoo.g72.scene.element.Element;
import com.lpoo.g72.scene.Position;

public class RightCommand implements Command {
    Element element;

    public RightCommand(Element element){
        this.element = element;
    }

    @Override
    public void execute() {
        Position position = element.getPosition().right();
        element.setPosition(position);
    }
}
