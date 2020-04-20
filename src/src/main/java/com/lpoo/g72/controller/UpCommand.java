package com.lpoo.g72.controller;

import com.lpoo.g72.scene.Element;
import com.lpoo.g72.scene.Position;

public class UpCommand implements Command {

    public UpCommand(){}

    @Override
    public void execute(Element element) {
        Position position = element.getPosition().up();
        element.setPosition(position);
    }
}
