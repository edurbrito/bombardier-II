package com.lpoo.g72.gui.visualElement.visualMonsters;

import com.lpoo.g72.gui.visualElement.VisualElement;

public class VisualDimorphodon extends VisualElement {

    public VisualDimorphodon() {
        super(new char[]{'=','-','/','-','~'}, new String[]{"#054bc1", "#05A9C1", "#054bc1","#05A9C1","#054bc1"} );
    }

    @Override
    public void animation() {
        if (this.form[2] == '/'){
            this.form[2] = '\\';
            this.form[4] = '«';
        }
        else{
            this.form[2] = '/';
            this.form[4] = '~';
        }
    }
}
