package com.lpoo.g72.gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.creator.LisbonSceneCreator;
import com.lpoo.g72.gui.visualElement.VisualElement;
import com.lpoo.g72.gui.visualElement.visualMonsters.VisualPteranodon;
import com.lpoo.g72.model.Model;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Monster;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class SceneTest {
    private Scene scene;

    @Test
    public void testSceneDimensions(){

        Random r = new Random();
        for(int i = 0; i < 5; i++){
            int w = r.nextInt(300) + 1;
            int h = r.nextInt(300) + 1;

            List<VisualElement> visualPteranodons = new ArrayList<>();
            visualPteranodons.add(new VisualPteranodon());
            visualPteranodons.add(new VisualPteranodon());
            visualPteranodons.add(new VisualPteranodon());

            this.scene = new Scene(w, h,"StubScene", visualPteranodons);

            assertEquals(w, scene.getWidth());
            assertEquals(h, scene.getHeight());
            assertEquals(3, scene.getVisualMonsters().size());
            assertEquals("StubScene", scene.getName());
            assertEquals(0, scene.getSceneBlocks());
        }
    }

    @Before
    public void initScene(){
        this.scene = Mockito.spy(new LisbonSceneCreator().createScene(50,100));
    }

    @Test
    public void testDraw(){
        assertEquals(50, scene.getWidth());
        assertEquals(100, scene.getHeight());

        int vmonstersSize = this.scene.getVisualMonsters().size();
        assertEquals(3,vmonstersSize);

        Model model = new Model();
        Monster monster = new Monster(new Position(12,32));

        model.addMonster(monster);
        model.addMonster(monster);
        model.addMonster(monster);

        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        model.setHelicopter(new Helicopter(new Position(0,1),5,5));
        this.scene.draw(graphics,model.getHelicopter(),model.getMonsters());

        Mockito.verify(this.scene,Mockito.times(1)).draw(Mockito.any(), Mockito.any(),Mockito.any());
        Mockito.verify(this.scene,Mockito.times(1)).drawSceneBuildings(Mockito.any());

        Mockito.verify(graphics, Mockito.times(2 + vmonstersSize)).enableModifiers(Mockito.any());
        Mockito.verify(graphics, Mockito.times(2 + vmonstersSize)).setBackgroundColor(Mockito.any());
        Mockito.verify(graphics, Mockito.times(1 + vmonstersSize*this.scene.getVisualMonsters().get(0).getForm().length)).setForegroundColor(Mockito.any());

        Mockito.verify(this.scene,Mockito.times(1)).drawVisualMonsters(Mockito.any(), Mockito.any());

    }

    @Test
    public void removeBuildings(){
        int x = 0, y = 0;

        assertEquals(0, this.scene.removeBuilding(x,y));

        x = 200;

        assertEquals(0, this.scene.removeBuilding(x,y));

        x = 50;

        assertEquals(0, this.scene.removeBuilding(x,y));


        x = 30;
        y = 40;

        assertEquals(0, this.scene.removeBuilding(x,y));

        x = 30;
        y = 90;

        assertEquals(1, this.scene.removeBuilding(x,y));
        assertEquals(' ', this.scene.getBuildings()[this.scene.getHeight() - y - 5][ this.scene.getWidth() - x - 1]);

        x = 1;
        y = 92;

        assertEquals(2, this.scene.removeBuilding(x,y));
        assertEquals(' ', this.scene.getBuildings()[this.scene.getHeight() - y - 5][ this.scene.getWidth() - x - 1]);
        assertEquals(' ', this.scene.getBuildings()[this.scene.getHeight() - y - 5][ this.scene.getWidth() - x]);

        x = this.scene.getWidth() - 2;
        y = 93;

        assertEquals(2, this.scene.removeBuilding(x,y));
        assertEquals(' ', this.scene.getBuildings()[this.scene.getHeight() - y - 5][ this.scene.getWidth() - x - 1]);
        assertEquals(' ', this.scene.getBuildings()[this.scene.getHeight() - y - 5][0]);
    }
}
