package com.lpoo.g72.states.bombDropStates;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.gui.VisualHelicopter;
import com.lpoo.g72.states.BombDropState;

public class CanDropBomb extends BombDropState {

    public CanDropBomb(VisualHelicopter visualHelicopter) {
        super(visualHelicopter);
    }

    @Override
    public void bombDropAction(Gui.Key key){
        if(key == Gui.Key.SPACE)
            visualHelicopter.setBombDropState(new BombDropStart(visualHelicopter));
    }
}
