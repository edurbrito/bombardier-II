package com.lpoo.g72.gui.visualElement;

public class VisualHelicopter extends VisualElement {

    public VisualHelicopter() {
        super(new char[]{'/', '-', 'Õ'}, new String[]{"#2a2a2a", "#e60000", "#2a2a2a"});
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
