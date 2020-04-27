package com.lpoo.g72.commands.directional;

import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.element.Element;

public class DownCommand extends DirectionalCommand {
    public DownCommand(Element element) {
        super(element);
    }

    public void execute() {
        Position position = element.getPosition().down();
        element.setPosition(position);
    }
}
