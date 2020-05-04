package com.lpoo.g72.controller;

import com.lpoo.g72.commands.CommandInvoker;
import com.lpoo.g72.gui.visualElement.VisualElement;
import com.lpoo.g72.model.element.Element;

import java.time.Instant;

public abstract class ElementController {
    protected VisualElement visualElement;
    protected Element element;
    protected CommandInvoker commandInvoker;

    protected double movingTime;
    protected Instant lastForwardMove;

    protected int maxWidth;
    protected int altitude;

    public ElementController(VisualElement visualElement, Element element) {
        this.visualElement = visualElement;
        this.element = element;
        this.commandInvoker = CommandInvoker.getInstance();
    }

    public VisualElement getVisualElement() {
        return this.visualElement;
    }

    public Element getElement() {
        return this.element;
    }

    public int getElementX(){
        return this.element.getPosition().getX();
    }

    public int getElementY(){
        return this.element.getPosition().getY();
    }

    protected abstract void move();

}
