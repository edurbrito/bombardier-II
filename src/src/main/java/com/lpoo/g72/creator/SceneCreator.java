package com.lpoo.g72.creator;
import com.lpoo.g72.scene.Helicopter;
import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.Scene;
import java.util.Random;

public abstract class SceneCreator {
    protected Scene scene;

    public abstract Scene createScene(int width, int height);

    protected char[][] generateBuildings(int width, int height, Random r, int heightFactor) {

        int minHeight = 1;
        int maxHeight = height * 5 / 10;

        char[] btops = {'T', 'L', 'N', 'H', 'S', 'R'};

        char[][] buildings = new char[height][width];

        for(int h = 0; h < height; ++h){
            for(int w = 0; w < width; w += 2){ // Every building has 2 units of width

                if(w + 1 == width) // For an odd width not reaching the end without a building
                    w--;

                if(minHeight > h) // Sets a minimum for every building's height
                    buildings[h][w] = buildings[h][w+1] = '#';

                else{

                    if(h < maxHeight + 1){

                        if(buildings[h-1][w] == '#'){

                            if (heightFactor(r,heightFactor) && h < maxHeight){ // If the current building does not have a top yet
                                buildings[h][w] = buildings[h][w+1] = '#'; // It can grow some meters more
                            }
                            else{

                                int randomChar = r.nextInt(5); // Chooses a top for the building
                                buildings[h][w] = buildings[h][w+1] = btops[randomChar]; // Sets it

                            }

                        }
                        else{ // If the current building already has a top - finished growing

                            buildings[h][w] = buildings[h][w+1] = ' ';

                        }
                    }
                    else{ // If reached the maximum height allowed

                        buildings[h][w] = buildings[h][w+1] = ' ';

                    }
                }
            }
        }

        return buildings;
    }

    public boolean heightFactor(Random r, int factor){

        boolean heightProb = r.nextInt(2) == 0;

        for(int i = 0; i < factor; i++){ // Giving it more chances to create a taller building
            heightProb = heightProb || r.nextInt(2) == 0;
        }

        return heightProb;
    }

}