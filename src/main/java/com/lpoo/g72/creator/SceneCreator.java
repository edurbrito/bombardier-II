package com.lpoo.g72.creator;

import com.lpoo.g72.gui.Scene;

import java.util.Random;

public abstract class SceneCreator {
    protected Scene scene;

    public abstract Scene createScene(int width, int height);

    protected char[][] generateBuildings(int width, int height, Random r, int heightFactor) {

        int minHeight = 1;
        int maxHeight = height * 4 / 5; // ratio of maximum building height in relation to the scene dimension

        char[] btops = {'T', 'L', 'N', 'H', 'S', 'R'}; // building allowed tops

        char[][] buildings = new char[height][width];

        for (int h = 0; h < height; ++h) {
            for (int w = 0; w < width; w += 2) { // Every building has 2 units of width

                if (w + 1 == width) // For an odd width not reaching the end without a building
                    w--;

                if (minHeight > h) // Sets a minimum for every building's height
                    buildings[h][w] = buildings[h][w + 1] = '#';

                else {

                    if (h < maxHeight + 1) { // while not reaching the maxHeight

                        if (buildings[h - 1][w] == '#') { // If the current building does not have a top yet

                            if (continueToGrow(r, heightFactor) && h < maxHeight) { // See continueToGrow function for more details

                                buildings[h][w] = buildings[h][w + 1] = '#'; // It can grow some meters more

                            } else {

                                int randomChar = r.nextInt(5); // Chooses a top for the building
                                buildings[h][w] = buildings[h][w + 1] = btops[randomChar]; // Sets it

                            }

                        } else { // If the current building already has a top - finished growing

                            buildings[h][w] = buildings[h][w + 1] = ' ';

                        }
                    } else { // If reached the maximum height allowed

                        buildings[h][w] = buildings[h][w + 1] = ' ';

                    }
                }
            }
        }

        return buildings;
    }

    private boolean continueToGrow(Random r, int factor) {

        // This will return a true or false answer for continuing to "grow" the current building in the loop.
        // The answer is based on some probability associated with the @param factor.
        // The greater the factor, the greater the probability of answering true
        // and the greater the probability of creating a taller building, on the previous function.

        boolean heightProb = r.nextInt(2) == 0;

        for (int i = 0; i < factor; i++) { // Giving it <@param factor> more chances to create a taller building
            heightProb = heightProb || r.nextInt(2) == 0;
        }

        return heightProb;
    }

}