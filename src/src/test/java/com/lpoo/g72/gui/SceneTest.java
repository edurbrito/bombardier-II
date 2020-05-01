package com.lpoo.g72.gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.TextGUIGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.virtual.DefaultVirtualTerminal;
import com.lpoo.g72.creator.LisbonSceneCreator;
import com.lpoo.g72.gui.Scene;
import com.lpoo.g72.gui.visualElement.VisualMonster;
import com.lpoo.g72.model.Model;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Monster;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.smartcardio.TerminalFactory;
import java.io.IOException;
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

            List<VisualMonster> monsters = new ArrayList<>();
            int nMonsters = 1 + r.nextInt(10);
            for(int j = 0; j < nMonsters; j++){
                monsters.add(new VisualMonster());
            }

            Scene scene = new Scene(w, h, monsters);

            assertEquals(w, scene.getWidth());
            assertEquals(h, scene.getHeight());
            assertEquals(nMonsters, scene.getVisualMonsters().size());
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

        this.scene.draw(graphics,model.getMonsters());

        Mockito.verify(this.scene,Mockito.times(1)).draw(Mockito.any(), Mockito.any());
        Mockito.verify(this.scene,Mockito.times(1)).drawSceneBuildings(Mockito.any());

        Mockito.verify(graphics, Mockito.times(1+vmonstersSize)).enableModifiers(Mockito.any());
        Mockito.verify(graphics, Mockito.times(vmonstersSize)).setBackgroundColor(Mockito.any());
        Mockito.verify(graphics, Mockito.times(1+vmonstersSize*this.scene.getVisualMonsters().get(0).getForm().length)).setForegroundColor(Mockito.any());

        Mockito.verify(this.scene,Mockito.times(1)).drawVisualMonsters(Mockito.any(), Mockito.any());

    }
}
