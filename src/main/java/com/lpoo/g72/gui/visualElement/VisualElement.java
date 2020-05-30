package com.lpoo.g72.gui.visualElement;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.model.element.Element;

public abstract class VisualElement {

    protected char[] form;
    protected String[] colorPallet;

    public VisualElement(char[] form, String[] colorPallet) {
        this.form = form;
        this.colorPallet = colorPallet;
    }

    public char[] getForm() {
        return this.form;
    }

    public void setForm(char[] form) {
        this.form = form;
    }

    public String[] getColorPallet() {
        return this.colorPallet;
    }

    public void setColorPallet(String[] colorPallet) {
        this.colorPallet = colorPallet;
    }

    public abstract void animation();

    public void draw(TextGraphics graphics, Element element) {
        graphics.enableModifiers(SGR.BOLD);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#cccccc"));

        for (int i = 0; i < this.form.length; i++) {
            graphics.setForegroundColor(TextColor.Factory.fromString(this.colorPallet[i]));
            graphics.setCharacter(element.getPosition().getX() + i, element.getPosition().getY(), this.form[i]);
        }
    }
}
