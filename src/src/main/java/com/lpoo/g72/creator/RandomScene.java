package com.lpoo.g72.creator;

import com.lpoo.g72.scene.Helicopter;
import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.Scene;

import java.util.Random;

public class RandomScene extends SceneCreator {

    @Override
    public Scene createScene(int width, int height) {

        Scene scene = super.createScene(width,height);
        scene.setHelicopter(new Helicopter(new Position(0,0)," \\-O", "#2a2a2a"));
        // TODO: Create some elements like buildings, monsters, etc

        return scene;
    }

    @Override
    char[][] generateBuildings(int width, int height) {

        int minHeight = 1;

        char[] btops = {'T', 'L', 'N', 'H', 'S', 'R'};
        Random r = new Random();

        char[][] buildings = new char[height][width];
        for(int h = 0; h < height; ++h){
            for(int w = 0; w < width; ++w){
                if(minHeight > h)
                    buildings[h][w] = '#';
                else{
                    if(buildings[h-1][w] == '#'){
                        if (r.nextInt(2) == 0 || r.nextInt(2) == 0){ // Giving it more chances to create a taller building
                            buildings[h][w] = '#';
                            continue;
                        }

                        int randomChar = r.nextInt(5);
                        buildings[h][w] = btops[randomChar];
                    }
                    else{
                        buildings[h][w] = ' ';
                    }
                }
            }
        }

        return buildings;
    }
}
