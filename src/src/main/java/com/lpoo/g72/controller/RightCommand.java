package com.lpoo.g72.controller;

import com.lpoo.g72.scene.Element;
import com.lpoo.g72.scene.Position;

public class RightCommand implements Command {

    public RightCommand(){}
    @Override
    public void execute(Element element) {
        Position position = element.getPosition().right();
        element.setPosition(position);
    }
}
