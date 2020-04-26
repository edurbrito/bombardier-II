package com.lpoo.g72.scene.visualElement;

import com.lpoo.g72.scene.element.Element;

public class VisualMonster extends VisualElement {

    public VisualMonster(Element element) {
        super(element, new char[]{'<','-','/','-','{'}, new String[]{"#2f4a28", "#28910d", "#2f4a28","#28910d","#2f4a28"});
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
