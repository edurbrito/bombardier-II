package com.lpoo.g72.controller;

import com.lpoo.g72.scene.Element;
import com.lpoo.g72.scene.Position;

public class DownCommand implements Command{

    public DownCommand(){}
    @Override
    public void execute(Element element) {
        Position position = element.getPosition().down();
        element.setPosition(position);
    }
}
