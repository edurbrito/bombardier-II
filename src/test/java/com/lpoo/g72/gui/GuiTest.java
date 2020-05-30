package com.lpoo.g72.gui;

import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Monster;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GuiTest {
    private Gui gui;

    @Before
    public void init() throws IOException {
        this.gui = Mockito.spy(new Gui(12, 15));
        List<Scene> scenes = new ArrayList<>();
        scenes.add(Mockito.mock(Scene.class));
        scenes.add(Mockito.mock(Scene.class));
        scenes.add(Mockito.mock(Scene.class));
        this.gui.setMenuOptions(scenes);
    }

    @Test
    public void testInit() {
        assertEquals(12, this.gui.getWidth());
        assertEquals(15, this.gui.getHeight());

        assertNotNull(this.gui.getScreen());

        assertNull(this.gui.getScene());
        assertNotNull(this.gui.getMessageDrawer());

    }

    @Test
    public void testDraw() throws IOException {

        Scene scene = Mockito.mock(Scene.class);

        this.gui.setScene(scene);

        assertNotNull(this.gui.getScene());

        Helicopter helicopter = new Helicopter(new Position(0, 1), 4, 5);

        List<Monster> monsterList = new ArrayList<>();
        monsterList.add(new Monster(new Position(12, 11)));
        monsterList.add(new Monster(new Position(12, 13)));
        monsterList.add(new Monster(new Position(12, 15)));

        this.gui.drawScene(helicopter, monsterList, 0, 0);

        Mockito.verify(scene, Mockito.times(1)).draw(Mockito.any(), Mockito.any(), Mockito.any());

        this.gui.closeScreen();

    }

}
