package com.lpoo.g72.gui.visualElement;

public class VisualHelicopter extends VisualElement {

    public VisualHelicopter() {
        super(new char[]{'/', '-', 'Õ'}, new String[]{"#212121", "#cd0000", "#212121"});
    }

    public void animation() {
        if (this.form[0] == '/'){
            this.form[0] = '\\';
            this.form[2] = 'O';
        }
        else{
            this.form[0] = '/';
            this.form[2] = 'Õ';
        }
    }

}
