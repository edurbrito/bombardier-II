package com.lpoo.g72.scene.visualElement;

import com.lpoo.g72.scene.element.Element;

public abstract class VisualElement {

    protected Element element;
    protected char[] form;
    protected String[] colorPallet;

    public VisualElement(Element element, char[] form, String[] colorPallet) {
        this.element = element;
        this.form = form;
        this.colorPallet = colorPallet;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public char[] getForm() {
        return form;
    }

    public void setForm(char[] form) {
        this.form = form;
    }

    public String[] getColorPallet() {
        return colorPallet;
    }

    public void setColorPallet(String[] colorPallet) {
        this.colorPallet = colorPallet;
    }
    
    public int getElementX(){
        return this.element.getPosition().getX();
    }
    
    public int getElementY(){
        return this.element.getPosition().getY();
    }

    public abstract void animation();
}
