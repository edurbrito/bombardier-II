package com.lpoo.g72.gui.visualElement;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.model.element.Element;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Missile;

public class VisualHelicopter extends VisualElement {

    private VisualHorizontalMissile horizontalMissile;
    private VisualVerticalMissile verticalMissile;

    public VisualHelicopter() {
        super(new char[]{'/', '-', 'Õ'}, new String[]{"#2a2a2a", "#e60000", "#2a2a2a"});
        this.horizontalMissile = new VisualHorizontalMissile();
        this.verticalMissile = new VisualVerticalMissile();
    }

    public void animation() {
        if (this.form[0] == '/'){
            this.form[0] = '\\';
            this.form[2] = 'O';
        }
        else{
            this.form[0] = '/';
            this.form[2] = 'Õ';
        }

        this.horizontalMissile.animation();
        this.verticalMissile.animation();
    }

    @Override
    public void draw(TextGraphics graphics, Element element){
        super.draw(graphics,element);

        Helicopter helicopter = (Helicopter) element;

        for(Missile missile : helicopter.getHorizontalMissiles()){
            this.horizontalMissile.draw(graphics, missile);
        }

        for(Missile missile : helicopter.getVerticalMissiles()){
            this.verticalMissile.draw(graphics, missile);
        }
    }

}
