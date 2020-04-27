package com.lpoo.g72.commands.directional;

import com.lpoo.g72.commands.Command;
import com.lpoo.g72.scene.element.Element;

public abstract class DirectionalCommand implements Command {
    Element element;

    public DirectionalCommand(Element element){
        this.element = element;
    }
}
