package com.lpoo.g72.gui;

import com.lpoo.g72.scene.Helicopter;
import com.lpoo.g72.states.BombDropState;
import com.lpoo.g72.states.CanDropBomb;

import java.time.Instant;

public class VisualHelicopter {

    private Helicopter helicopter;
    private char[] form;
    private String[] colorPallet;

    BombDropState bombDropState;

    private double droppingBombTime;
    private double bombsDisabledTime;

    private Instant lastBombDropStart;
    private Instant lastBombDropEnd;

    public VisualHelicopter(Helicopter helicopter) {
        this.lastBombDropStart = null;
        this.lastBombDropEnd = null;

        this.helicopter = helicopter;
        this.form = new char[]{'/', '-', 'Õ'};
        this.colorPallet = new String[]{"#2a2a2a", "#e60000", "#2a2a2a"};
    }

    public void wingRotation() {
        if (this.form[0] == '/'){
            this.form[0] = '\\';
            this.form[2] = 'O';
        }
        else{
            this.form[0] = '/';
            this.form[2] = 'Õ';
        }
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }

    public void setHelicopter(Helicopter helicopter) {
        this.helicopter = helicopter;
    }

    public int getHelicopterX() {
        return this.helicopter.getPosition().getX();
    }

    public int getHelicopterY() {
        return this.helicopter.getPosition().getY();
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

    public double getLaunchingTime() {
        return droppingBombTime;
    }

    public double getUnlaunchableTime() {
        return bombsDisabledTime;
    }

    public Instant getLastBombDropStart() {
        return lastBombDropStart;
    }

    public void setLastBombDropStart(Instant lastBombDropStart) {
        this.lastBombDropStart = lastBombDropStart;
    }

    public Instant getLastBombDropEnd() {
        return lastBombDropEnd;
    }

    public void setLastBombDropEnd(Instant lastBombDropEnd) {
        this.lastBombDropEnd = lastBombDropEnd;
    }

    public void setBombDropState(BombDropState state) {
        this.bombDropState = state;
    }

    public void bombDropAction() {
        bombDropState.bombDropAction();
    }

    public void setStartBombDropProperties(double droppingBombTime, double bombsDisabledTime) {
        this.droppingBombTime = droppingBombTime;
        this.bombsDisabledTime = bombsDisabledTime;
        this.bombDropState = new CanDropBomb(this);
    }

    public boolean canDropBomb() {
        return bombDropState.getClass() == CanDropBomb.class;
    }
}
