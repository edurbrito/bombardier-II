package com.lpoo.g72.creator;

import com.lpoo.g72.gui.visualElement.VisualElement;
import com.lpoo.g72.gui.visualElement.visualMonsters.VisualPteranodon;
import com.lpoo.g72.gui.Scene;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class SceneCreatorTest {

    @Test
    public void testDimensions(){

        SceneCreator sc1 = new OportoSceneCreator();
        assertEquals(200,sc1.createScene(50,200).getHeight());
        assertEquals(50,sc1.createScene(50,200).getWidth());

        SceneCreator sc2 = new LisbonSceneCreator();
        assertEquals(11,sc2.createScene(50,11).getHeight());
        assertEquals(85,sc2.createScene(85,300).getWidth());


        SceneCreator sc3 = new RandomSceneCreator();
        assertEquals(27,sc2.createScene(34,27).getHeight());
        assertEquals(89,sc2.createScene(89,303).getWidth());

    }

    class SceneCreatorStub extends SceneCreator{

        @Override
        public Scene createScene(int width, int height) {
            List<VisualElement> visualMonsters = new ArrayList<>();

            visualMonsters.add(new VisualPteranodon());
            visualMonsters.add(new VisualPteranodon());
            visualMonsters.add(new VisualPteranodon());

            this.scene = new Scene(width, height,"StubScene", visualMonsters);
            this.scene.setBuildings(this.generateBuildings(width,height,new Random(),4));

            return this.scene;
        }

        @Override
        protected char[][] generateBuildings(int width, int height, Random r, int heightFactor){

            char[][] buildings = new char[height][width];

            for(int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    buildings[h][w] = '#';
                }
            }

            return buildings;
        }
    }

    @Test
    public void testStub(){

        SceneCreatorStub stub = new SceneCreatorStub();
        Scene scene = stub.createScene(20,80);

        assertEquals(20, scene.getWidth());
        assertEquals(80, scene.getHeight());

        assertEquals(scene.getHeight(), scene.getBuildings().length);
        for(int h = 0; h < scene.getHeight(); h++){
            assertEquals(scene.getWidth(), scene.getBuildings()[h].length);
            for(int w = 0; w < scene.getWidth(); w++){
                assertEquals('#', scene.getBuildings()[h][w]);
            }
        }

        assertEquals(3, scene.getVisualMonsters().size());

    }

    private int buildingCounter(Scene scene){
        int counter = 0;

        assertEquals(scene.getHeight(), scene.getBuildings().length);
        for(int h = 0; h < scene.getHeight(); h++){
            assertEquals(scene.getWidth(), scene.getBuildings()[h].length);
            for(int w = 0; w < scene.getWidth(); w++){
                if(scene.getBuildings()[h][w] == '#')
                    counter++;
            }
        }

        return counter;
    }

    @Test
    public void testOporto(){
        OportoSceneCreator oporto = new OportoSceneCreator();
        Scene scene = oporto.createScene(50,100);

        assertEquals(50, scene.getWidth());
        assertEquals(100, scene.getHeight());

        assertEquals(2, scene.getVisualMonsters().size());

        assertEquals(scene.getHeight(), scene.getBuildings().length);

        assertEquals(346,buildingCounter(scene));
    }

    @Test
    public void testLisbon(){
        LisbonSceneCreator lisbon = new LisbonSceneCreator();
        Scene scene = lisbon.createScene(30,70);

        assertEquals(30, scene.getWidth());
        assertEquals(70, scene.getHeight());

        assertEquals(3, scene.getVisualMonsters().size());

        assertEquals(510,buildingCounter(scene));
    }

    @Test
    public void testRandom(){
        RandomSceneCreator random = new RandomSceneCreator();
        Scene scene = random.createScene(41,90);

        assertEquals(41, scene.getWidth());
        assertEquals(90, scene.getHeight());

        assertEquals(4, scene.getVisualMonsters().size());

        assertTrue(scene.getBuildings().length > 0);
    }
}
