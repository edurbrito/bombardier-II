package com.lpoo.g72.gui.visualElement;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.model.element.Element;

public class VisualVerticalMissile extends VisualElement{

    public VisualVerticalMissile() {
        super(new char[]{'_', '|', 'W'}, new String[]{"#b84b0f", "#fd610c", "#b84b0f"});
    }

    @Override
    public void animation() {

    }

    @Override
    public void draw(TextGraphics graphics, Element element){
        graphics.enableModifiers(SGR.BOLD);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#C0C0C0"));

        for (int i = 0; i < this.form.length; i++) {
            graphics.setForegroundColor(TextColor.Factory.fromString(this.colorPallet[i]));
            graphics.setCharacter(element.getPosition().getX(),element.getPosition().getY()+i , this.form[i]);
        }
    }
}
