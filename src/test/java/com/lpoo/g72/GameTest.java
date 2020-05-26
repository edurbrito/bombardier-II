package com.lpoo.g72;

import com.lpoo.g72.controller.Controller;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.model.Model;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameTest {

    private Game game;

    @Test
    public void testGameStub() throws IOException {

        this.game = new GameStub();
        this.game.start();

        assertNotNull(this.game.getGui());
        assertNotNull(this.game.getModel());
        assertNotNull(this.game.getController());

        int fixedWidth = 100, fixedHeight = 50;

        assertEquals("Gui must always have a pre-determined fixed Width", fixedWidth, this.game.getGui().getWidth());
        assertEquals("Gui must always have a pre-determined fixed Height", fixedHeight, this.game.getGui().getHeight());

    }

    @Ignore
    @Test
    public void testGame() throws IOException {
        this.game = new Game();

        this.game.start();

        assertNotNull(this.game.getController());
        assertNotNull(this.game.getGui());
        assertNotNull(this.game.getModel());
        int fixedWidth = 100, fixedHeight = 50;

        assertEquals("Gui must always have a pre-determined fixed Width", fixedWidth, this.game.getGui().getWidth());
        assertEquals("Gui must always have a pre-determined fixed Height", fixedHeight, this.game.getGui().getHeight());

    }

    class GameStub extends Game {
        private Model model;
        private Gui gui;
        private Controller controller;

        @Override
        public void start() throws IOException {
            this.gui = new Gui(100, 50);
            this.model = new Model();
            this.controller = new Controller(this.gui, this.model);
        }

        public Gui getGui() {
            return this.gui;
        }

        public Model getModel() {
            return model;
        }

        public Controller getController() {
            return controller;
        }
    }
}
