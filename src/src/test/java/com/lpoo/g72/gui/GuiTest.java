package com.lpoo.g72.gui;

import com.lpoo.g72.gui.visualElement.VisualHelicopter;
import com.lpoo.g72.gui.visualElement.VisualMonster;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Monster;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiTest {
    private Gui gui;
    @Before
    public void init() throws IOException {
        this.gui = new Gui(12,15);
    }

    @Test
    public void testInit() {
        assertEquals(12,this.gui.getWidth());
        assertEquals(15,this.gui.getHeight());

        assertNotNull(this.gui.getVisualHelicopter());

        assertNotNull(this.gui.getScreen());

        assertNull(this.gui.getScene());

    }

    @Test
    public void testDraw() throws IOException {
        List<VisualMonster> visualMonsterList = new ArrayList<>();
        visualMonsterList.add(new VisualMonster());
        visualMonsterList.add(new VisualMonster());
        visualMonsterList.add(new VisualMonster());

        Scene scene = Mockito.mock(Scene.class);

        this.gui.setScene(scene);

        assertNotNull(this.gui.getScene());

        Helicopter helicopter = new Helicopter(new Position(0,1));

        List<Monster> monsterList = new ArrayList<>();
        monsterList.add(new Monster(new Position(12,11)));
        monsterList.add(new Monster(new Position(12,13)));
        monsterList.add(new Monster(new Position(12,15)));

        this.gui.draw(helicopter,monsterList);

        Mockito.verify(scene,Mockito.times(1)).draw(Mockito.any(),Mockito.any());

        this.gui.closeScreen();
    }

}
