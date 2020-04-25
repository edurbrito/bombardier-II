package com.lpoo.g72.commands;

import com.lpoo.g72.scene.Element;
import com.lpoo.g72.scene.Position;

public class DownCommand implements Command{
    Element element;

    public DownCommand(Element element){
        this.element = element;
    }

    @Override
    public void execute() {
        Position position = element.getPosition().down();
        element.setPosition(position);
    }
}
