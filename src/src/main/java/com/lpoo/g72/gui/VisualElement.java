package com.lpoo.g72.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.lpoo.g72.scene.Position;

public class VisualElement {
    String str;
    String color;

    public VisualElement(String str, String color) {
        this.str = str;
        this.color = color;
    }

    public void draw(TextGraphics graphics, Position position){
        graphics.enableModifiers(SGR.BOLD);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#C0C0C0"));
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.putString(position.getX(), position.getY(),this.str);
    }
}
