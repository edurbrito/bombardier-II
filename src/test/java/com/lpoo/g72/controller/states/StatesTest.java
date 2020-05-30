package com.lpoo.g72.controller.states;

import com.lpoo.g72.controller.Controller;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.model.Model;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class StatesTest {

    @Test
    public void testStates() {
        Gui gui = Mockito.mock(Gui.class);
        Model model = Mockito.mock(Model.class);
        ControllerStub controller = Mockito.spy(new ControllerStub(gui, model));

        try {
            controller.setState(new MenuState(controller));
            controller.getState().action(Gui.Key.DOWN);
            Mockito.verify(controller,Mockito.times(1)).menu(Gui.Key.DOWN);
            Mockito.verify(controller,Mockito.times(1)).dummyProcudure();
            controller.getState().action(Gui.Key.UP);
            Mockito.verify(controller,Mockito.times(1)).menu(Gui.Key.UP);
            Mockito.verify(controller,Mockito.times(2)).dummyProcudure();
            controller.getState().action(Gui.Key.ENTER);
            Mockito.verify(controller,Mockito.times(1)).menu(Gui.Key.ENTER);
            assertSame(controller.getState().getClass(), GameState.class);

            controller.getState().action(Gui.Key.DOWN);
            Mockito.verify(controller,Mockito.times(3)).dummyProcudure();
            controller.getState().action(Gui.Key.UP);
            Mockito.verify(controller,Mockito.times(4)).dummyProcudure();
            controller.getState().action(Gui.Key.SPACE);
            Mockito.verify(controller,Mockito.times(5)).dummyProcudure();
            controller.getState().action(Gui.Key.QUIT);
            Mockito.verify(controller, Mockito.times(1)).play(Gui.Key.QUIT);
            assertSame(controller.getState().getClass(), MenuState.class);

            controller.setState(new GameState(controller));
            controller.getState().action(Gui.Key.RIGHT);
            Mockito.verify(controller, Mockito.times(1)).play(Gui.Key.RIGHT);
            assertSame(controller.getState().getClass(), EndGame.class);

            controller.getState().action(Gui.Key.DOWN);
            Mockito.verify(controller, Mockito.times(1)).endGame(Gui.Key.DOWN);
            controller.getState().action(Gui.Key.UP);
            Mockito.verify(controller, Mockito.times(1)).endGame(Gui.Key.UP);
            controller.getState().action(Gui.Key.SPACE);
            Mockito.verify(controller, Mockito.times(1)).endGame(Gui.Key.SPACE);
            controller.getState().action(Gui.Key.RIGHT);
            Mockito.verify(controller, Mockito.times(1)).endGame(Gui.Key.RIGHT);

            controller.getState().action(Gui.Key.QUIT);
            Mockito.verify(controller, Mockito.times(1)).endGame(Gui.Key.QUIT);
            assertSame(controller.getState().getClass(), MenuState.class);

            controller.setState(new Highscores(controller));
            controller.getState().action(Gui.Key.QUIT);
            Mockito.verify(controller, Mockito.times(1)).highscores(Gui.Key.QUIT);
            assertSame(controller.getState().getClass(), MenuState.class);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private class ControllerStub extends Controller {

        public ControllerStub(Gui gui, Model model) {
            super(gui, model);
        }

        private State state;

        @Override
        public void menu(Gui.Key key) {
            if(key == Gui.Key.DOWN || key == Gui.Key.UP){
                this.dummyProcudure();
            }
            else if(key == Gui.Key.ENTER){
                this.setState(new GameState(this));
            }
        }

        @Override
        public void play(Gui.Key key) {
            if(key == Gui.Key.DOWN || key == Gui.Key.UP || key == Gui.Key.SPACE){
                this.dummyProcudure();
            }
            else if(key == Gui.Key.RIGHT){ // Just to Simulate the Ending
                this.state = new EndGame(this);
            }
            else if(key == Gui.Key.QUIT){
                this.state = new MenuState(this);
            }
        }

        @Override
        public void endGame(Gui.Key key) {
            if(key == Gui.Key.QUIT){
                this.state = new MenuState(this);
            }
        }

        @Override
        public void highscores(Gui.Key key) {
            if(key == Gui.Key.QUIT){
                this.state = new MenuState(this);
            }
        }

        public void setState(State state) {
            this.state = state;
        }

        public State getState() {
            return state;
        }

        public void dummyProcudure(){
            int i = 1 + 1;
        }
    }
}
