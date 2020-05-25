package com.lpoo.g72.controller.states;

import com.lpoo.g72.controller.Controller;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.model.Model;
import org.junit.Test;
import org.mockito.Mockito;

public class StatesTest {

    @Test
    public void testStates() {
        Gui gui = Mockito.mock(Gui.class);
        Model model = Mockito.mock(Model.class);
        Controller controller = Mockito.spy(new ControllerStub(gui, model));

        MenuState menuState = new MenuState(controller);
        GameState gameState = new GameState(controller);
        EndGame endGame = new EndGame(controller);
        Highscores highscores = new Highscores(controller);

        try {
            menuState.action(Gui.Key.DOWN);
            Mockito.verify(controller, Mockito.times(1)).menu(Gui.Key.DOWN);
            menuState.action(Gui.Key.UP);
            Mockito.verify(controller, Mockito.times(1)).menu(Gui.Key.UP);
            menuState.action(Gui.Key.ENTER);
            Mockito.verify(controller, Mockito.times(1)).menu(Gui.Key.ENTER);

            gameState.action(Gui.Key.DOWN);
            Mockito.verify(controller, Mockito.times(1)).play(Gui.Key.DOWN);
            gameState.action(Gui.Key.UP);
            Mockito.verify(controller, Mockito.times(1)).play(Gui.Key.UP);
            gameState.action(Gui.Key.SPACE);
            Mockito.verify(controller, Mockito.times(1)).play(Gui.Key.SPACE);
            gameState.action(Gui.Key.RIGHT);
            Mockito.verify(controller, Mockito.times(1)).play(Gui.Key.RIGHT);

            endGame.action(Gui.Key.DOWN);
            Mockito.verify(controller, Mockito.times(1)).endGame(Gui.Key.DOWN);
            endGame.action(Gui.Key.UP);
            Mockito.verify(controller, Mockito.times(1)).endGame(Gui.Key.UP);
            endGame.action(Gui.Key.SPACE);
            Mockito.verify(controller, Mockito.times(1)).endGame(Gui.Key.SPACE);
            endGame.action(Gui.Key.RIGHT);
            Mockito.verify(controller, Mockito.times(1)).endGame(Gui.Key.RIGHT);

            highscores.action(Gui.Key.RIGHT);
            Mockito.verify(controller, Mockito.times(1)).highscores(Gui.Key.RIGHT);
        } catch (Exception e) {
        }
    }

    private class ControllerStub extends Controller {

        public ControllerStub(Gui gui, Model model) {
            super(gui, model);
        }

        @Override
        public void menu(Gui.Key key) {

        }

        @Override
        public void play(Gui.Key key) {

        }

        @Override
        public void endGame(Gui.Key key) {

        }

        @Override
        public void highscores(Gui.Key key) {

        }
    }
}
