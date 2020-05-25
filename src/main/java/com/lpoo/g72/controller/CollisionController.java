package com.lpoo.g72.controller;

import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.model.Model;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Missile;
import com.lpoo.g72.model.element.Monster;

import java.util.List;

public class CollisionController {
    private final Gui gui;
    private Model model;

    public CollisionController(Gui gui, Model model) {
        this.gui = gui;
        this.model = model;
    }

    public int horizontalCollisions(){
        List<Missile> horizontalMissiles = this.model.getHelicopter().getHorizontalMissiles();
        List<Monster> monsters = this.model.getMonsters();
        Helicopter helicopter = this.model.getHelicopter();

        int points = 0;

        for(Monster monster : monsters){
            for(Missile missile : horizontalMissiles){
                if(horizontalCollisionChecker(missile.getPosition(),monster.getPosition()) && monster.isAlive()){
                    missile.setExploded();
                    monster.kill();
                    points += 1;
                }
            }

            if(horizontalCollisionChecker(helicopter.getPosition(),monster.getPosition()) && monster.isAlive()){
                helicopter.loseLife();
                monster.kill();
            }
        }

        return points;
    }

    private boolean horizontalCollisionChecker(Position pos1, Position pos2){
        return pos1.equals(pos2) || pos1.equals(pos2.right()) || pos1.equals(pos2.right().right())|| pos1.equals(pos2.left());
    }

    public int blocksDestroyed(){
        int blocks = 0;

        List<Missile> verticalMissiles = this.model.getHelicopter().getVerticalMissiles();

        for(Missile missile : verticalMissiles){
            if(!missile.hasExploded() && missile.isActive()){
                int x = missile.getX();
                int y = missile.getY() % (this.gui.getScene().getBuildings().length - 2);

                for(int i = 0; i < 3; i++){
                    blocks += this.gui.getScene().removeBuilding( x, y + i);
                }
            }
        }
        return blocks;
    }

    public boolean buildingsCollisions(){
        Helicopter helicopter = this.model.getHelicopter();
        int heliSize = this.gui.getVisualHelicopter().getForm().length - 1;
        if(this.gui.getScene().removeBuilding(helicopter.getX() + heliSize,helicopter.getY()) > 0){
            return true;
        }
        return false;
    }
}
