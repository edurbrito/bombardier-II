package com.lpoo.g72.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.gui.visualElement.VisualHorizontalMissile;
import com.lpoo.g72.gui.visualElement.VisualVerticalMissile;
import com.lpoo.g72.gui.visualElement.VisualElement;
import com.lpoo.g72.gui.visualElement.VisualMonster;
import com.lpoo.g72.model.element.Missile;
import com.lpoo.g72.model.element.Monster;

import java.util.List;

public class Scene {
    int width;
    int height;
    private char[][] buildings;
    private List<VisualMonster> visualMonsters;
    private VisualElement[] visualMissiles;

    public Scene(int width, int height, List<VisualMonster> visualMonsters){
        this.width = width;
        this.height = height;
        this.visualMonsters = visualMonsters;

        this.visualMissiles = new VisualElement[]{new VisualVerticalMissile(), new VisualHorizontalMissile()};
    }

    public void setBuildings(char[][] buildings) {
        this.buildings = buildings;
    }

    public char[][] getBuildings() {
        return this.buildings;
    }

    public List<VisualMonster> getVisualMonsters() {
        return this.visualMonsters;
    }

    public void draw(TextGraphics graphics, List<Monster> monsters, List<Missile> horizontalMissiles, List<Missile> verticalMissiles ){
        this.drawSceneBuildings(graphics);
        this.drawVisualMonsters(graphics, monsters);
        this.drawVisualMissiles(graphics,horizontalMissiles, verticalMissiles);
    }

    public void drawSceneBuildings(TextGraphics graphics) {
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#2a2a2a"));

        for (int h = 0; h < this.height; ++h) {
            for (int w = 0; w < this.width; ++w) {
                graphics.putString(this.width - w - 1, this.height - h - 5, String.valueOf(this.buildings[h][w]));
            }
        }
    }

    public void drawVisualMonsters(TextGraphics graphics, List<Monster> monsters){
        for(int i = 0; i< this.visualMonsters.size(); i++){
            this.visualMonsters.get(i).draw(graphics,monsters.get(i));
        }
    }

    public void drawVisualMissiles(TextGraphics graphics, List<Missile> verticalMissiles, List<Missile> horizontalMissiles){

        for(int i = 0; i < verticalMissiles.size(); i++){
            this.visualMissiles[0].draw(graphics, verticalMissiles.get(i));
        }

        for(int i = 0; i < horizontalMissiles.size(); i++){
            this.visualMissiles[1].draw(graphics, horizontalMissiles.get(i));
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
