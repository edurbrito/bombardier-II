package com.lpoo.g72.gui.visualElement.visualMissiles;

import com.lpoo.g72.gui.visualElement.VisualElement;

public class VisualHorizontalMissile extends VisualElement {

    public VisualHorizontalMissile() {
        super(new char[]{'»', '»', '-', '-', '►'}, new String[]{"#0e539a", "#0e539a", "#233F5C", "#233F5C", "#0e539a"});
    }

    @Override
    public void animation() {
        if (this.form[1] == '»') {
            this.form[1] = '>';
            this.form[0] = '>';
        } else {
            this.form[0] = '»';
            this.form[1] = '»';
        }
    }
}
