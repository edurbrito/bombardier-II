package com.lpoo.g72.creator;

import com.lpoo.g72.scene.Helicopter;
import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.Scene;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class SceneCreatorTest {

    @Test
    public void testDimensions(){

        SceneCreator sc1 = new OportoScene();
        assertEquals(200,sc1.createScene(50,200).getHeight());
        assertEquals(50,sc1.createScene(50,200).getWidth());

        SceneCreator sc2 = new LisbonScene();
        assertEquals(11,sc2.createScene(50,11).getHeight());
        assertEquals(85,sc2.createScene(85,300).getWidth());


        SceneCreator sc3 = new RandomScene();
        assertEquals(27,sc2.createScene(34,27).getHeight());
        assertEquals(89,sc2.createScene(89,303).getWidth());

    }

    class SceneCreatorStub extends SceneCreator{

        @Override
        public Scene createScene(int width, int height) {

            this.scene = new Scene(width, height);
            this.scene.setHelicopter(new Helicopter(new Position(0,0)));
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

        assertNotNull(scene.getHelicopter());

        assertEquals(scene.getHeight(), scene.getBuildings().length);
        for(int h = 0; h < scene.getHeight(); h++){
            assertEquals(scene.getWidth(), scene.getBuildings()[h].length);
            for(int w = 0; w < scene.getWidth(); w++){
                assertEquals('#', scene.getBuildings()[h][w]);
            }
        }

        assertTrue(stub.heightFactor(new Random(19),3));
        assertTrue(stub.heightFactor(new Random(22),3));
        assertTrue(stub.heightFactor(new Random(31),3));
        assertTrue(stub.heightFactor(new Random(42),3));
        assertTrue(stub.heightFactor(new Random(25),3));

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
        OportoScene oporto = new OportoScene();
        Scene scene = oporto.createScene(50,100);

        assertEquals(50, scene.getWidth());
        assertEquals(100, scene.getHeight());

        assertNotNull(scene.getHelicopter());

        assertEquals(scene.getHeight(), scene.getBuildings().length);

        assertEquals(218,buildingCounter(scene));
    }

    @Test
    public void testLisbon(){
        LisbonScene lisbon = new LisbonScene();
        Scene scene = lisbon.createScene(30,70);

        assertEquals(30, scene.getWidth());
        assertEquals(70, scene.getHeight());

        assertNotNull(scene.getHelicopter());

        assertEquals(176,buildingCounter(scene));
    }

    @Test
    public void testRandom(){
        RandomScene random = new RandomScene();
        Scene scene = random.createScene(41,90);

        assertEquals(41, scene.getWidth());
        assertEquals(90, scene.getHeight());

        assertNotNull(scene.getHelicopter());
    }

}
