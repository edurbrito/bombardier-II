package com.lpoo.g72.scene.visualElement;

import com.lpoo.g72.scene.element.Helicopter;

public class VisualHelicopter extends VisualElement {

    public VisualHelicopter(Helicopter helicopter) {
        super(helicopter, new char[]{'/', '-', 'Õ'}, new String[]{"#2a2a2a", "#e60000", "#2a2a2a"});
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
