package com.lpoo.g72.controller.states;
import com.lpoo.g72.controller.Controller;
import com.lpoo.g72.gui.Gui;

import java.io.IOException;

public abstract class State {
        protected Controller controller;

        State(Controller controller){
            this.controller = controller;
        }

        public abstract void action(Gui.Key key) throws IOException;
}
