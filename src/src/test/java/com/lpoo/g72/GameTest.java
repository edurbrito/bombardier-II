package com.lpoo.g72;
import com.lpoo.g72.gui.Gui;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    public void helper() throws IOException, InterruptedException {
        this.game = new Game();
        this.game.start();
    }

    @Test
    public void gameGui() throws IOException, InterruptedException {
        helper();

        int fixedWidth = 100, fixedHeight = 50;

        assertEquals("Gui must always have a pre-determined fixed Width", fixedWidth, this.game.getGui().getWidth());
        assertEquals("Gui must always have a pre-determined fixed Height", fixedHeight, this.game.getGui().getHeight());
    }

    @Test
    public void mockGameGui() throws IOException, InterruptedException {

        Game g = Mockito.mock(Game.class);

        Mockito.when(g.getGui()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(g).setGui(Mockito.any(Gui.class));
        Mockito.doNothing().when(g).start();

        Random r = new Random();

        Mockito.doAnswer(invocation -> {
            int fixedWidth = 1 + r.nextInt(200);
            int fixedHeight = 1 + r.nextInt(100);
            g.setGui(new Gui(fixedWidth, fixedHeight));
            assertEquals("Gui must always have a pre-determined fixed Width", fixedWidth, g.getGui().getWidth());
            assertEquals("Gui must always have a pre-determined fixed Height", fixedHeight, g.getGui().getHeight());
            return null;
        }).when(g).start();

        for(int i = 0; i < 5 ; i++){
            g.start();
        }
    }
}
