package com.lpoo.g72.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.gui.visualElement.VisualElement;
import com.lpoo.g72.gui.visualElement.VisualHelicopter;
import com.lpoo.g72.gui.visualElement.VisualMonster;
import com.lpoo.g72.model.element.Monster;

import java.util.Collection;
import java.util.List;

public class Scene {
    int width;
    int height;
    private char[][] buildings;
    private List<VisualMonster> visualMonsters;

    public Scene(int width, int height, List<VisualMonster> visualMonsters){
        this.width = width;
        this.height = height;
        this.visualMonsters = visualMonsters;
    }

    public void setBuildings(char[][] buildings) {
        this.buildings = buildings;
    }

    public char[][] getBuildings() {
        return this.buildings;
    }

    public List<VisualMonster> getVisualMonsters() {
        return visualMonsters;
    }

    public void draw(TextGraphics graphics, List<Monster> monsters){
        drawSceneBuildings(graphics);
        drawVisualMonsters(graphics, monsters);
    }

    public void drawSceneBuildings(TextGraphics graphics) {
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#2a2a2a"));

        for (int h = 0; h < height; ++h) {
            for (int w = 0; w < width; ++w) {
                graphics.putString(width - w - 1, height - h - 5, String.valueOf(buildings[h][w]));
            }
        }
    }

    public void drawVisualMonsters(TextGraphics graphics, List<Monster> monsters){
        for(int i = 0; i< visualMonsters.size(); i++){
            visualMonsters.get(i).draw(graphics,monsters.get(i));
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
