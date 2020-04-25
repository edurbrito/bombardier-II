package com.lpoo.g72.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.scene.Position;
import com.lpoo.g72.states.BombDropState;
import com.lpoo.g72.states.CanDropBomb;

import java.time.Instant;

public class VisualHelicopter {

    WING wing;

    BombDropState bombDropState;

    private double droppingBombTime;
    private double bombsDisabledTime;

    private Instant lastBombDropStart;
    private Instant lastBombDropEnd;

    public enum WING {LAUNCHING, NORMAL};

    public VisualHelicopter() {
        this.wing = WING.NORMAL;
        this.lastBombDropStart = null;
        this.lastBombDropEnd = null;
    }

    public void draw(TextGraphics graphics, Position position){
        graphics.enableModifiers(SGR.BOLD);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#C0C0C0"));

        graphics.setForegroundColor(TextColor.Factory.fromString("#2a2a2a"));
        if(wing != WING.LAUNCHING){
            graphics.setCharacter(position.getX(), position.getY(),'\\');
        }
        else{
            graphics.setCharacter(position.getX(), position.getY(),'/');
        }

        graphics.setForegroundColor(TextColor.Factory.fromString("#e60000"));
        graphics.setCharacter(position.getX()+1, position.getY(),'-');

        graphics.setForegroundColor(TextColor.Factory.fromString("#2a2a2a"));
        graphics.setCharacter(position.getX()+2, position.getY(),'O');
    }

    public void setWing(WING wing){
        this.wing = wing;
    }

    public WING getWing() {
        return wing;
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

    public Instant getLastBombDropEnd() {
        return lastBombDropEnd;
    }

    public void setBombDropState(BombDropState state) {
        this.bombDropState = state;
    }

    public void setLastBombDropStart(Instant lastBombDropStart) {
        this.lastBombDropStart = lastBombDropStart;
    }

    public void setLastBombDropEnd(Instant lastBombDropEnd) {
        this.lastBombDropEnd = lastBombDropEnd;
    }

    public void bombDropAction(){
        bombDropState.bombDropAction();
    }

    public void setStartBombDropProperties(double droppingBombTime, double bombsDisabledTime){
        this.droppingBombTime = droppingBombTime;
        this.bombsDisabledTime = bombsDisabledTime;
        this.bombDropState = new CanDropBomb(this);
    }

    public boolean canDropBomb() {
        return bombDropState.getClass() == CanDropBomb.class;
    }
}
