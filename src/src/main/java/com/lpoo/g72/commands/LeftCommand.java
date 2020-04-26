package com.lpoo.g72.commands;

import com.lpoo.g72.scene.element.Element;
import com.lpoo.g72.scene.Position;

public class LeftCommand implements Command {
    Element element;

    public LeftCommand(Element element){
        this.element = element;
    }

    @Override
    public void execute() {
        Position position = element.getPosition().left();
        element.setPosition(position);
    }
}
