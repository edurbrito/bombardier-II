package com.lpoo.g72.controller;

import com.lpoo.g72.commands.CommandInvoker;

import java.time.Instant;

public abstract class ElementController {
    protected CommandInvoker commandInvoker;

    protected double deltaTime;
    protected Instant lastForwardMove;

    protected int maxWidth;
    protected int altitude;

    public ElementController(int maxWidth, int altitude, double deltaTime) {

        this.maxWidth = maxWidth;
        this.altitude = altitude;
        this.deltaTime = deltaTime;
        this.lastForwardMove = Instant.now();
        this.commandInvoker = CommandInvoker.getInstance();
    }

    public double getDeltaTime() {
        return this.deltaTime;
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    public int getAltitude() {
        return this.altitude;
    }

    protected abstract void move();

}
