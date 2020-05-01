package com.lpoo.g72.gui.visualElement;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.lpoo.g72.model.ElementStub;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Element;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Monster;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class VisualElementTest {

   protected class VisualElementStub extends VisualElement{

        public VisualElementStub(char[] form, String[] colorPallet) {
            super(form, colorPallet);
        }

        @Override
        public void animation() {
            char[] temp = new char[this.form.length];
            for(int i = 0; i < this.form.length; i++){
                temp[i] = this.form[(i + 1) % this.form.length];
            }

            this.form = temp;
        }

        @Override
        public void draw(TextGraphics graphics, Element element){
            for(int i = 0; i < this.form.length; i++){
                element.getPosition().getX();
                element.getPosition().getY();
            }
        }
    }

    private VisualElement visualElement;

    @Before
    public void createStub(){

        this.visualElement = new VisualElementStub( new char[]{'a','b','c'},new String[]{"A","B","C"});

    }

    @Test
    public void testInit(){

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);

        assertEquals(3, visualElement.getForm().length);
        assertEquals('a', visualElement.getForm()[0]);
        assertEquals('b', visualElement.getForm()[1]);
        assertEquals('c', visualElement.getForm()[2]);

        assertEquals(3, visualElement.getColorPallet().length);
        assertEquals("C", visualElement.getColorPallet()[2]);
        assertEquals("B", visualElement.getColorPallet()[1]);
        assertEquals("A", visualElement.getColorPallet()[0]);

        visualElement.animation();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);
        assertEquals(3, visualElement.getForm().length);
        assertEquals('b', visualElement.getForm()[0]);
        assertEquals('c', visualElement.getForm()[1]);
        assertEquals('a', visualElement.getForm()[2]);

        visualElement.animation();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);
        assertEquals(3, visualElement.getForm().length);
        assertEquals('c', visualElement.getForm()[0]);
        assertEquals('a', visualElement.getForm()[1]);
        assertEquals('b', visualElement.getForm()[2]);

        visualElement.animation();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);
        assertEquals(3, visualElement.getForm().length);
        assertEquals('a', visualElement.getForm()[0]);
        assertEquals('b', visualElement.getForm()[1]);
        assertEquals('c', visualElement.getForm()[2]);
    }


    @Test
    public void testSetters(){

        visualElement.setForm(new char[]{'q','w','e','r','t', 'y'});
        visualElement.setColorPallet(new String[]{"A","B","C","D","E","F"});

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);
        assertEquals(6, visualElement.getForm().length);
        assertEquals('q', visualElement.getForm()[0]);
        assertEquals('w', visualElement.getForm()[1]);
        assertEquals('e', visualElement.getForm()[2]);
        assertEquals('r', visualElement.getForm()[3]);
        assertEquals('t', visualElement.getForm()[4]);
        assertEquals('y', visualElement.getForm()[5]);

        assertEquals(6, visualElement.getColorPallet().length);
        assertEquals("F", visualElement.getColorPallet()[5]);
        assertEquals("E", visualElement.getColorPallet()[4]);
        assertEquals("D", visualElement.getColorPallet()[3]);
        assertEquals("C", visualElement.getColorPallet()[2]);
        assertEquals("B", visualElement.getColorPallet()[1]);
        assertEquals("A", visualElement.getColorPallet()[0]);

        visualElement.animation();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);
        assertEquals(6, visualElement.getForm().length);
        assertEquals('w', visualElement.getForm()[0]);
        assertEquals('e', visualElement.getForm()[1]);
        assertEquals('r', visualElement.getForm()[2]);
        assertEquals('t', visualElement.getForm()[3]);
        assertEquals('y', visualElement.getForm()[4]);
        assertEquals('q', visualElement.getForm()[5]);

        visualElement.animation();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);
        assertEquals(6, visualElement.getForm().length);
        assertEquals('e', visualElement.getForm()[0]);
        assertEquals('r', visualElement.getForm()[1]);
        assertEquals('t', visualElement.getForm()[2]);
        assertEquals('y', visualElement.getForm()[3]);
        assertEquals('q', visualElement.getForm()[4]);
        assertEquals('w', visualElement.getForm()[5]);

        visualElement.animation();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);
        assertEquals(6, visualElement.getForm().length);
        assertEquals('r', visualElement.getForm()[0]);
        assertEquals('t', visualElement.getForm()[1]);
        assertEquals('y', visualElement.getForm()[2]);
        assertEquals('q', visualElement.getForm()[3]);
        assertEquals('w', visualElement.getForm()[4]);
        assertEquals('e', visualElement.getForm()[5]);

    }

    @Test
    public void testDraw(){
        Position position = Mockito.spy(new Position(1,2));
        Element element = Mockito.spy(new ElementStub(position));
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        this.visualElement.draw(graphics, element);

        Mockito.verify(element,Mockito.times(this.visualElement.getForm().length * 2)).getPosition();
        Mockito.verify(position,Mockito.times(this.visualElement.getForm().length)).getY();
        Mockito.verify(position,Mockito.times(this.visualElement.getForm().length)).getX();

        Mockito.clearInvocations(element);
        Mockito.clearInvocations(position);
        Mockito.clearInvocations(graphics);

        this.visualElement = new VisualHelicopter();

        this.visualElement.draw(graphics,element);

        Mockito.verify(element,Mockito.times(this.visualElement.getForm().length * 2)).getPosition();
        Mockito.verify(position,Mockito.times(this.visualElement.getForm().length)).getY();
        Mockito.verify(position,Mockito.times(this.visualElement.getForm().length)).getX();

        Mockito.verify(graphics, Mockito.times(1)).enableModifiers(Mockito.any());
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(Mockito.any());


        Mockito.verify(graphics, Mockito.times(this.visualElement.getForm().length)).setForegroundColor(Mockito.any());

        Mockito.verify(graphics, Mockito.times(1)).setCharacter(1,2 ,'/');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(2,2 ,'-');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(3,2 ,'Õ');

        Mockito.clearInvocations(element);
        Mockito.clearInvocations(position);
        Mockito.clearInvocations(graphics);

        this.visualElement = new VisualMonster();

        this.visualElement.draw(graphics,element);

        Mockito.verify(element,Mockito.times(this.visualElement.getForm().length * 2)).getPosition();
        Mockito.verify(position,Mockito.times(this.visualElement.getForm().length)).getY();
        Mockito.verify(position,Mockito.times(this.visualElement.getForm().length)).getX();

        Mockito.verify(graphics, Mockito.times(1)).enableModifiers(Mockito.any());
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(Mockito.any());


        Mockito.verify(graphics, Mockito.times(this.visualElement.getForm().length)).setForegroundColor(Mockito.any());

        Mockito.verify(graphics, Mockito.times(1)).setCharacter(1,2 ,'<');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(2,2 ,'-');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(3,2 ,'/');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(4,2 ,'-');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(5,2 ,'{');

    }

    @Test
    public void testVisualHelicopter(){
        visualElement = new VisualHelicopter();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);

        assertEquals(3, visualElement.getForm().length);
        assertEquals('/', visualElement.getForm()[0]);
        assertEquals('-', visualElement.getForm()[1]);
        assertEquals('Õ', visualElement.getForm()[2]);

        assertEquals(3, visualElement.getColorPallet().length);
        assertEquals("#2a2a2a", visualElement.getColorPallet()[2]);
        assertEquals("#e60000", visualElement.getColorPallet()[1]);
        assertEquals("#2a2a2a", visualElement.getColorPallet()[0]);

    }

    @Test
    public void testVisualMonster(){
        visualElement = new VisualMonster();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);

        assertEquals(5, visualElement.getForm().length);
        assertEquals('<', visualElement.getForm()[0]);
        assertEquals('-', visualElement.getForm()[1]);
        assertEquals('/', visualElement.getForm()[2]);
        assertEquals('-', visualElement.getForm()[3]);
        assertEquals('{', visualElement.getForm()[4]);

        assertEquals(5, visualElement.getColorPallet().length);
        assertEquals("#2f4a28", visualElement.getColorPallet()[4]);
        assertEquals("#28910d", visualElement.getColorPallet()[3]);
        assertEquals("#2f4a28", visualElement.getColorPallet()[2]);
        assertEquals("#28910d", visualElement.getColorPallet()[1]);
        assertEquals("#2f4a28", visualElement.getColorPallet()[0]);

    }

    @Test
    public void testConcreteAnimationsVH(){
        visualElement = new VisualHelicopter();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);

        assertEquals(3, visualElement.getForm().length);
        assertEquals('/', visualElement.getForm()[0]);
        assertEquals('-', visualElement.getForm()[1]);
        assertEquals('Õ', visualElement.getForm()[2]);

        visualElement.animation();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);

        assertEquals(3, visualElement.getForm().length);
        assertEquals('\\', visualElement.getForm()[0]);
        assertEquals('-', visualElement.getForm()[1]);
        assertEquals('O', visualElement.getForm()[2]);

        visualElement.animation();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);

        assertEquals(3, visualElement.getForm().length);
        assertEquals('/', visualElement.getForm()[0]);
        assertEquals('-', visualElement.getForm()[1]);
        assertEquals('Õ', visualElement.getForm()[2]);
    }


    @Test
    public void testConcreteAnimationsVM(){
        visualElement = new VisualMonster();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);

        assertEquals(5, visualElement.getForm().length);
        assertEquals('<', visualElement.getForm()[0]);
        assertEquals('-', visualElement.getForm()[1]);
        assertEquals('/', visualElement.getForm()[2]);
        assertEquals('-', visualElement.getForm()[3]);
        assertEquals('{', visualElement.getForm()[4]);

        visualElement.animation();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);

        assertEquals(5, visualElement.getForm().length);
        assertEquals('<', visualElement.getForm()[0]);
        assertEquals('-', visualElement.getForm()[1]);
        assertEquals('\\', visualElement.getForm()[2]);
        assertEquals('-', visualElement.getForm()[3]);
        assertEquals('<', visualElement.getForm()[4]);

        visualElement.animation();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);

        assertEquals(5, visualElement.getForm().length);
        assertEquals('<', visualElement.getForm()[0]);
        assertEquals('-', visualElement.getForm()[1]);
        assertEquals('/', visualElement.getForm()[2]);
        assertEquals('-', visualElement.getForm()[3]);
        assertEquals('{', visualElement.getForm()[4]);
    }
}
