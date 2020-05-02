package com.lpoo.g72.gui.visualElement;

public class VisualMonster extends VisualElement {

    public VisualMonster() {
        super(new char[]{'<','-','/','-','{'}, new String[]{"#2f4a28", "#28910d", "#2f4a28","#28910d","#2f4a28"});
    }

    @Override
    public void animation() {
        if (this.form[2] == '/'){
            this.form[2] = '\\';
            this.form[4] = '<';
        }
        else{
            this.form[2] = '/';
            this.form[4] = '{';
        }
    }
}
