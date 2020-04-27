package com.lpoo.g72.controller;

import com.lpoo.g72.scene.visualElement.VisualElement;
import com.lpoo.g72.scene.element.Element;

import java.time.Instant;

public abstract class ElementController {

    protected VisualElement visualElement;
    protected Element element;

    protected double velocity;
    protected Instant lastForwardMove;

    protected int maxWidth;
    protected int altitude;

    public ElementController(VisualElement visualElement) {
        this.visualElement = visualElement;
        this.element = this.visualElement.getElement();
    }

    public VisualElement getVisualElement() {
        return this.visualElement;
    }

    public Element getElement() {
        return this.element;
    }

    public int getElementX(){
        return this.visualElement.getElementX();
    }

    public int getElementY(){
        return this.visualElement.getElementY();
    }

    protected abstract void move();

}
