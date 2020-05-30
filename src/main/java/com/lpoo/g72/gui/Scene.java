package com.lpoo.g72.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.gui.visualElement.VisualElement;
import com.lpoo.g72.gui.visualElement.VisualHelicopter;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Monster;

import java.util.List;

public class Scene {
    private final int width;
    private final int height;
    private final VisualHelicopter visualHelicopter;
    private char[][] buildings;
    private int sceneBlocks;
    private String name;
    private List<VisualElement> visualMonsters;

    public Scene(int width, int height, String name, List<VisualElement> visualMonsters) {
        this.width = width;
        this.height = height;
        this.name = name;
        this.visualHelicopter = new VisualHelicopter();
        this.visualMonsters = visualMonsters;
    }

    public void draw(TextGraphics graphics, Helicopter helicopter, List<Monster> monsters) {

        this.drawSceneBuildings(graphics);

        this.drawVisualMonsters(graphics, monsters);

        this.visualHelicopter.draw(graphics, helicopter);
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

    public void drawVisualMonsters(TextGraphics graphics, List<Monster> monsters) {
        for (int i = 0; i < monsters.size(); i++) {
            if (monsters.get(i).isAlive())
                this.visualMonsters.get(i).draw(graphics, monsters.get(i));
        }
    }

    public int removeBuilding(int x, int y) {
        int destroyedBlocks = 0;

        if (x >= this.width) return 0;

        if (this.buildings[this.height - y - 5][this.width - x - 1] != ' ') {
            destroyedBlocks++;
            this.buildings[this.height - y - 5][this.width - x - 1] = ' ';
        }

        if (x == 1 && this.buildings[this.height - y - 5][this.width - x] != ' ') {
            destroyedBlocks++;
            this.buildings[this.height - y - 5][this.width - x] = ' ';
        } else if (x == this.width - 2 && this.buildings[this.height - y - 5][0] != ' ') {
            destroyedBlocks++;
            this.buildings[this.height - y - 5][0] = ' ';
        }

        return destroyedBlocks;
    }

    private void setSceneBlocks() {
        this.sceneBlocks = 0;
        for (char[] building : this.buildings) {
            for (char c : building) {
                if (c != ' ')
                    this.sceneBlocks++;
            }
        }
    }

    public char[][] getBuildings() {
        return this.buildings;
    }

    public void setBuildings(char[][] buildings) {
        this.buildings = buildings;
        this.setSceneBlocks();
    }

    public List<VisualElement> getVisualMonsters() {
        return this.visualMonsters;
    }

    public int getSceneBlocks() {
        return this.sceneBlocks;
    }

    public String getName() {
        return this.name;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public VisualHelicopter getVisualHelicopter() {
        return visualHelicopter;
    }

}