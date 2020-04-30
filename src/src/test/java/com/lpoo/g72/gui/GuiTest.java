package com.lpoo.g72.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Element;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.gui.visualElement.VisualElement;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.when;

public class GuiTest {

    /*class GuiStub extends Gui {

        public GuiStub(int width, int height) throws IOException {
            super(width,height);
        }

        public void drawScoreBar(TextGraphics graphics, Scene scene){
            graphics.setForegroundColor(TextColor.Factory.fromString("#e60000"));
            graphics.drawLine(0, scene.getHeight() - 4, 8, scene.getHeight() - 4, '=');

            graphics.drawLine(scene.getWidth() - 9, scene.getHeight() - 4, scene.getWidth(), scene.getHeight() - 4, '=');

            graphics.setForegroundColor(TextColor.Factory.fromString("#2a2a2a"));
            graphics.putString(10, scene.getHeight() - 4, "Blocks: ");
            graphics.putString(30, scene.getHeight() - 4, "City: ");
            graphics.putString(scene.getWidth() - 45, scene.getHeight() - 4, "Score: ");
            graphics.putString(scene.getWidth() - 20, scene.getHeight() - 4, "Lives: ");
        }

    }

    class StubElement extends VisualElement {

        public StubElement(Element element, char[] form, String[] colorPallet) {
            super(element, form, colorPallet);
        }

        @Override
        public void animation() {}
    }

    @Test
    public void testDrawScoreBar() throws IOException {

        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Scene scene = Mockito.mock(Scene.class);

        GuiStub gui = new GuiStub(100,100);
        gui.drawScoreBar(graphics,scene);

        Mockito.verify(graphics,Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#e60000"));
        Mockito.verify(graphics,Mockito.times(1)).drawLine(0, scene.getHeight() - 4, 8, scene.getHeight() - 4, '=');
        Mockito.verify(graphics,Mockito.times(1)).drawLine(scene.getWidth() - 9, scene.getHeight() - 4, scene.getWidth(), scene.getHeight() - 4, '=');
        Mockito.verify(graphics,Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#2a2a2a"));
        Mockito.verify(graphics,Mockito.times(1)).putString(10, scene.getHeight() - 4, "Blocks: ");
        Mockito.verify(graphics,Mockito.times(1)).putString(30, scene.getHeight() - 4, "City: ");
        Mockito.verify(graphics,Mockito.times(1)).putString(scene.getWidth() - 45, scene.getHeight() - 4, "Score: ");
        Mockito.verify(graphics,Mockito.times(1)).putString(scene.getWidth() - 20, scene.getHeight() - 4, "Lives: ");

    }

    @Test
    public void testDrawBuildings() throws IOException {
*//*
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Scene scene = Mockito.mock(Scene.class);

        GuiStub gui = new GuiStub(100,100);
        gui.drawSceneBuildings(graphics,scene);

        Mockito.verify(graphics,Mockito.times(1)).enableModifiers(SGR.BOLD);
        Mockito.verify(graphics,Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#2a2a2a"));
        // TODO
        // How can we test the buildings?*//*
    }

    @Test
    public void testDrawElement() throws IOException {
        *//*Element element = Mockito.mock(Helicopter.class);
        when(element.getPosition()).thenReturn(new Position(0,0));
        char[] form = {'H'};
        String[] colorPallet =  {"#000000"};

        StubElement stubElement = new StubElement(element,form,colorPallet);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        GuiStub gui = new GuiStub(100,100);
        gui.drawElement(graphics,stubElement);

        Mockito.verify(graphics,Mockito.times(1)).enableModifiers(SGR.BOLD);
        Mockito.verify(graphics,Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#C0C0C0"));

        Mockito.verify(graphics,Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(colorPallet[0]));
        Mockito.verify(graphics,Mockito.times(1)).setCharacter(0,0,form[0]);*//*
    }
    
    @Test
    public void testDrawScene() throws IOException {

    }*/
}
