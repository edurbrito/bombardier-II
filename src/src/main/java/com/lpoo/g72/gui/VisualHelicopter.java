package com.lpoo.g72.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.scene.Position;

public class VisualHelicopter {

    WING wing;

    public enum WING {LAUNCHING, NORMAL};

    public VisualHelicopter() {
        this.wing = WING.NORMAL;
    }

    public void draw(TextGraphics graphics, Position position){
        graphics.enableModifiers(SGR.BOLD);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#C0C0C0"));

        graphics.setForegroundColor(TextColor.Factory.fromString("#2a2a2a"));
        if(wing != WING.LAUNCHING){
            graphics.setCharacter(position.getX(), position.getY(),'\\');
        }
        else{
            graphics.setCharacter(position.getX(), position.getY(),'/');
        }

        graphics.setForegroundColor(TextColor.Factory.fromString("#e60000"));
        graphics.setCharacter(position.getX()+1, position.getY(),'-');

        graphics.setForegroundColor(TextColor.Factory.fromString("#2a2a2a"));
        graphics.setCharacter(position.getX()+2, position.getY(),'O');
    }

    public void setWing(WING wing){
        this.wing = wing;
    }

    public WING getWing() {
        return wing;
    }
}
