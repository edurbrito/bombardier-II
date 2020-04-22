package com.lpoo.g72.command;

import com.lpoo.g72.scene.Element;
import com.lpoo.g72.scene.Position;

public class UpCommand implements Command {
    Element element;

    public UpCommand(Element element){
        this.element = element;
    }

    @Override
    public void execute() {
        Position position = element.getPosition().up();
        element.setPosition(position);
    }
}
