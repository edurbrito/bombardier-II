package com.lpoo.g72.scene.visualElement;

import com.lpoo.g72.scene.ElementStub;
import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.element.Element;
import com.lpoo.g72.scene.element.Helicopter;
import com.lpoo.g72.scene.element.Monster;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VisualElementTest {

    protected class VisualElementStub extends VisualElement{

        public VisualElementStub(ElementStub element, char[] form, String[] colorPallet) {
            super(element, form, colorPallet);
        }

        @Override
        public void animation() {
            char[] temp = new char[this.form.length];
            for(int i = 0; i < this.form.length; i++){
                temp[i] = this.form[(i + 1) % this.form.length];
            }

            this.form = temp;
        }
    }

    private VisualElement visualElement;

    @Before
    public void createStub(){

        this.visualElement = new VisualElementStub(new ElementStub(new Position(0,0)), new char[]{'a','b','c'},new String[]{"A","B","C"});

    }

    @Test
    public void testInit(){

        assertNotNull(visualElement.getElement());
        assertEquals(new Position(0,0), visualElement.getElement().getPosition());

        visualElement.getElement().setPosition(new Position(12,22));

        assertEquals(12,visualElement.getElementX());
        assertEquals(22,visualElement.getElementY());

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

        visualElement.setElement(new ElementStub(new Position(0,0)));

        assertNotNull(visualElement.getElement());
        assertEquals(ElementStub.class, visualElement.getElement().getClass());
        assertEquals(new Position(0,0), visualElement.getElement().getPosition());

        visualElement.setElement(new Helicopter(new Position(12,34)));

        assertNotNull(visualElement.getElement());
        assertEquals(Helicopter.class, visualElement.getElement().getClass());
        assertEquals(new Position(12,34), visualElement.getElement().getPosition());


        visualElement.setElement(new Monster(new Position(31,29)));

        assertNotNull(visualElement.getElement());
        assertEquals(Monster.class, visualElement.getElement().getClass());
        assertEquals(new Position(31,29), visualElement.getElement().getPosition());

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
    public void testVisualHelicopter(){
        visualElement = new VisualHelicopter(new Helicopter(new Position(12, 77)));

        assertNotNull(visualElement.getElement());
        assertEquals(Helicopter.class, visualElement.getElement().getClass());
        assertEquals(new Position(12,77), visualElement.getElement().getPosition());

        visualElement.getElement().setPosition(new Position(12,22));

        assertEquals(12,visualElement.getElementX());
        assertEquals(22,visualElement.getElementY());

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
        visualElement = new VisualMonster(new Monster(new Position(21, 42)));

        assertNotNull(visualElement.getElement());
        assertEquals(Monster.class, visualElement.getElement().getClass());
        assertEquals(new Position(21,42), visualElement.getElement().getPosition());

        visualElement.getElement().setPosition(new Position(8,2));

        assertEquals(8,visualElement.getElementX());
        assertEquals(2,visualElement.getElementY());

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
        visualElement = new VisualHelicopter(new Helicopter(new Position(12, 77)));

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
        visualElement = new VisualMonster(new Monster(new Position(12, 77)));

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
