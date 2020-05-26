package com.lpoo.g72.gui.visualElement.visualMissiles;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.gui.visualElement.VisualElement;
import com.lpoo.g72.model.element.Element;

public class VisualVerticalMissile extends VisualElement {

    public VisualVerticalMissile() {
        super(new char[]{'_', '|', 'W'}, new String[]{"#b84b0f", "#fd610c", "#b84b0f"});
    }

    @Override
    public void animation() {
        if (this.form[0] == '_'){
            this.form[0] = '.';
            this.form[2] = 'U';
        }
        else{
            this.form[0] = '_';
            this.form[2] = 'W';
        }
    }

    @Override
    public void draw(TextGraphics graphics, Element element){
        graphics.enableModifiers(SGR.BOLD);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#d1d1d1"));

        for (int i = 0; i < this.form.length; i++) {
            graphics.setForegroundColor(TextColor.Factory.fromString(this.colorPallet[i]));
            graphics.setCharacter(element.getPosition().getX(),element.getPosition().getY() + i , this.form[i]);
        }
    }
}
