package com.lpoo.g72.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.gui.visualElement.VisualMonster;
import com.lpoo.g72.model.element.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scene {
    private final int width;
    private final int height;
    private char[][] buildings;
    private int sceneBlocks;
    private String name;
    private List<VisualMonster> visualMonsterTypes;
    private List<VisualMonster> visualMonsters;

    public Scene(int width, int height, String name, List<VisualMonster> visualMonsterTypes, int numMonsters){
        this.width = width;
        this.height = height;
        this.name = name;
        this.visualMonsters = new ArrayList<>();
        this.visualMonsterTypes = visualMonsterTypes;

        this.setVisualMonsters(numMonsters);
    }

    public void setVisualMonsters(int numMonsters) {
        int pos;
        Random r = new Random();
        this.visualMonsters.clear();

        for(int i = 0; i < numMonsters; i++){
            pos = r.nextInt(this.visualMonsterTypes.size());
            this.visualMonsters.add(this.visualMonsterTypes.get(pos));
        }
    }

    public void setBuildings(char[][] buildings) {
        this.buildings = buildings;
        this.setSceneBlocks();
    }

    public char[][] getBuildings() {
        return this.buildings;
    }

    public List<VisualMonster> getVisualMonsters() {
        return this.visualMonsters;
    }

    public void draw(TextGraphics graphics, List<Monster> monsters, int destroyedBlocks){
        this.drawScoreBar(graphics, destroyedBlocks);
        this.drawSceneBuildings(graphics);
        this.drawVisualMonsters(graphics, monsters);
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
        for(int i = 0; i< monsters.size(); i++){
            if(monsters.get(i).isAlive())
                this.visualMonsters.get(i).draw(graphics,monsters.get(i));
        }
    }

    private void drawScoreBar(TextGraphics graphics, int destroyedBlocks) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#e60000"));
        graphics.drawLine(0, this.height - 4, 8, this.height - 4, '=');

        graphics.drawLine(this.width - 9, this.height - 4, this.width, this.height - 4, '=');

        graphics.setForegroundColor(TextColor.Factory.fromString("#2a2a2a"));
        graphics.putString(10, this.height - 4, this.blocksToString(destroyedBlocks));
        graphics.putString(30, this.height - 4, "City: " + name);
        graphics.putString(this.width - 45, this.height - 4, "Score: ");
        graphics.putString(this.width - 20, this.height - 4, "Lives: ");
    }

    private String blocksToString(int destroyedBlocks){
        return "Blocks: " + destroyedBlocks + "/" + this.sceneBlocks;
    }

    private void setSceneBlocks() {
        this.sceneBlocks = 0;
        for(int i = 0; i < this.buildings.length; i++){
            for(int j = 0; j < this.buildings[i].length; j++){
                if(this.buildings[i][j] != ' ')
                    this.sceneBlocks++;
            }
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int removeBuilding(int x, int y) {
        int destroyedBlocks = 0;

        if(this.buildings[this.height - y - 5][ this.width - x - 1] != ' '){
            destroyedBlocks ++;
            this.buildings[this.height - y - 5][ this.width - x - 1] = ' ';
        }

        if(x == 1 && this.buildings[this.height - y - 5][ this.width - x] != ' '){
            destroyedBlocks ++;
            this.buildings[this.height - y - 5][ this.width - x] = ' ';
        }

        else if(x == this.width - 2 && this.buildings[this.height - y - 5][ this.width - 2] != ' '){
            destroyedBlocks ++;
            this.buildings[this.height - y - 5][0] = ' ';
        }

        return destroyedBlocks;
    }
}