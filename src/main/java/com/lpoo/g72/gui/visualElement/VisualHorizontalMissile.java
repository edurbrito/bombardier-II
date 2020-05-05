package com.lpoo.g72.gui.visualElement;

public class VisualHorizontalMissile extends VisualElement{

    public VisualHorizontalMissile() {
        super(new char[]{'»', '»', '-', '-', '►'}, new String[]{"#0e539a", "#0e539a", "#233F5C", "#233F5C", "#0e539a"});
    }

    @Override
    public void animation() {
        if (this.form[0] == '»'){
            this.form[0] = '~';
            this.colorPallet[0] = "#b84b0f";
        }
        else{
            this.form[0] = '»';
            this.form[1] = '»';
            this.colorPallet[0] = "#0e539a";
        }
    }
}
