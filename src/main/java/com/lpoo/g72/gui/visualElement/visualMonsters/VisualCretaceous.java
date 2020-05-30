package com.lpoo.g72.gui.visualElement.visualMonsters;

import com.lpoo.g72.gui.visualElement.VisualElement;

public class VisualCretaceous extends VisualElement {

    public VisualCretaceous() {
        super(new char[]{'>', '-', '§', '-', '-'}, new String[]{"#870334", "#CD366E", "#870334", "#CD366E", "#870334"});
    }

    @Override
    public void animation() {
        if (this.form[0] == '>') {
            this.form[0] = '-';
            this.form[2] = '(';
            this.form[4] = '.';
        } else {
            this.form[0] = '>';
            this.form[2] = '§';
            this.form[4] = '-';
        }
    }
}
