package com.lpoo.g72.creator;

import org.junit.Test;

import static org.junit.Assert.*;

public class SceneCreatorTest {

    @Test
    public void testImplementations(){

        SceneCreator sc1 = new OportoScene();
        assertEquals(200,sc1.createScene(50,200).getHeight());
        assertEquals(50,sc1.createScene(50,200).getWidth());

        SceneCreator sc2 = new LisbonScene();
        assertEquals(10,sc2.createScene(50,10).getHeight());
        assertEquals(80,sc2.createScene(80,300).getWidth());

    }

}
