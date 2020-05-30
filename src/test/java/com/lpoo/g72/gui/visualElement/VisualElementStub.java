package com.lpoo.g72.gui.visualElement;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.model.element.Element;

public class VisualElementStub extends VisualElement {

    public VisualElementStub(char[] form, String[] colorPallet) {
        super(form, colorPallet);
    }

    @Override
    public void animation() {
        char[] temp = new char[this.form.length];
        for (int i = 0; i < this.form.length; i++) {
            temp[i] = this.form[(i + 1) % this.form.length];
        }

        this.form = temp;
    }

    @Override
    public void draw(TextGraphics graphics, Element element) {
        for (int i = 0; i < this.form.length; i++) {
            element.getPosition().getX();
            element.getPosition().getY();
        }
    }
}