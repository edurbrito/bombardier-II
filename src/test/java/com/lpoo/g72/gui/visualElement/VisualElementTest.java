package com.lpoo.g72.gui.visualElement;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.gui.visualElement.visualMissiles.VisualHorizontalMissile;
import com.lpoo.g72.gui.visualElement.visualMissiles.VisualVerticalMissile;
import com.lpoo.g72.gui.visualElement.visualMonsters.VisualCretaceous;
import com.lpoo.g72.gui.visualElement.visualMonsters.VisualDimorphodon;
import com.lpoo.g72.gui.visualElement.visualMonsters.VisualPteranodon;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Element;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Missile;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class VisualElementTest {

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
        Element element = Mockito.spy(new Helicopter(position,4,5));
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

        VisualVerticalMissile visualVerticalMissile = new VisualVerticalMissile();
        Missile missile = ((Helicopter) element).drop();
        missile.setPosition(element.getPosition().down().left());
        visualVerticalMissile.draw(graphics,missile);

        Mockito.verify(graphics, Mockito.times(1)).enableModifiers(Mockito.any());
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(Mockito.any());
        Mockito.verify(graphics, Mockito.times(visualVerticalMissile.getForm().length)).setForegroundColor(Mockito.any());

        Mockito.verify(graphics, Mockito.times(1)).setCharacter(0,3 ,'_');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(0,4 ,'|');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(0,5 ,'W');

        visualVerticalMissile.animation();
        visualVerticalMissile.draw(graphics,missile);

        Mockito.verify(graphics, Mockito.times(1)).setCharacter(0,3 ,'.');
        Mockito.verify(graphics, Mockito.times(2)).setCharacter(0,4 ,'|');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(0,5 ,'U');

        Mockito.clearInvocations(element);
        Mockito.clearInvocations(position);
        Mockito.clearInvocations(graphics);

        VisualHorizontalMissile visualHorizontalMissile = new VisualHorizontalMissile();
        Missile missile2 = ((Helicopter) element).shoot();
        missile2.setPosition(element.getPosition().right().right());
        visualHorizontalMissile.draw(graphics,missile2);

        Mockito.verify(graphics, Mockito.times(1)).enableModifiers(Mockito.any());
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(Mockito.any());
        Mockito.verify(graphics, Mockito.times(visualHorizontalMissile.getForm().length)).setForegroundColor(Mockito.any());

        Mockito.verify(graphics, Mockito.times(1)).setCharacter(3,2 ,'»');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(4,2 ,'»');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(5,2 ,'-');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(6,2 ,'-');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(7,2 ,'►');

        visualHorizontalMissile.animation();
        visualHorizontalMissile.draw(graphics,missile2);

        Mockito.verify(graphics, Mockito.times(1)).setCharacter(3,2 ,'>');
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(4,2 ,'>');
        Mockito.verify(graphics, Mockito.times(2)).setCharacter(5,2 ,'-');
        Mockito.verify(graphics, Mockito.times(2)).setCharacter(6,2 ,'-');
        Mockito.verify(graphics, Mockito.times(2)).setCharacter(7,2 ,'►');

        Mockito.clearInvocations(element);
        Mockito.clearInvocations(position);
        Mockito.clearInvocations(graphics);

        ((Helicopter) element).drop().activate();
        ((Helicopter) element).drop().activate();
        ((Helicopter) element).drop().activate();

        this.visualElement.draw(graphics,element);

        Mockito.verify(graphics, Mockito.times(4)).enableModifiers(Mockito.any());
        Mockito.verify(graphics, Mockito.times(4)).setBackgroundColor(Mockito.any());

        ((Helicopter) element).shoot().activate();
        ((Helicopter) element).shoot().activate();

        this.visualElement.draw(graphics,element);

        Mockito.verify(graphics, Mockito.times(10)).enableModifiers(Mockito.any());
        Mockito.verify(graphics, Mockito.times(10)).setBackgroundColor(Mockito.any());

        Mockito.clearInvocations(element);
        Mockito.clearInvocations(position);
        Mockito.clearInvocations(graphics);

        this.visualElement = new VisualPteranodon();

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
        visualElement = new VisualPteranodon();

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
        visualElement = new VisualPteranodon();

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

        //////////////////////////////////////////////////////

        visualElement = new VisualDimorphodon();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);

        assertEquals(5, visualElement.getForm().length);
        assertEquals('=', visualElement.getForm()[0]);
        assertEquals('-', visualElement.getForm()[1]);
        assertEquals('/', visualElement.getForm()[2]);
        assertEquals('-', visualElement.getForm()[3]);
        assertEquals('~', visualElement.getForm()[4]);

        visualElement.animation();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);

        assertEquals(5, visualElement.getForm().length);
        assertEquals('=', visualElement.getForm()[0]);
        assertEquals('-', visualElement.getForm()[1]);
        assertEquals('\\', visualElement.getForm()[2]);
        assertEquals('-', visualElement.getForm()[3]);
        assertEquals('«', visualElement.getForm()[4]);

        visualElement.animation();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);

        assertEquals(5, visualElement.getForm().length);
        assertEquals('=', visualElement.getForm()[0]);
        assertEquals('-', visualElement.getForm()[1]);
        assertEquals('/', visualElement.getForm()[2]);
        assertEquals('-', visualElement.getForm()[3]);
        assertEquals('~', visualElement.getForm()[4]);

        ////////////////////////////////////////////////////// '>','-','§','-','-'

        visualElement = new VisualCretaceous();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);

        assertEquals(5, visualElement.getForm().length);
        assertEquals('>', visualElement.getForm()[0]);
        assertEquals('-', visualElement.getForm()[1]);
        assertEquals('§', visualElement.getForm()[2]);
        assertEquals('-', visualElement.getForm()[3]);
        assertEquals('-', visualElement.getForm()[4]);

        visualElement.animation();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);

        assertEquals(5, visualElement.getForm().length);
        assertEquals('-', visualElement.getForm()[0]);
        assertEquals('-', visualElement.getForm()[1]);
        assertEquals('(', visualElement.getForm()[2]);
        assertEquals('-', visualElement.getForm()[3]);
        assertEquals('.', visualElement.getForm()[4]);

        visualElement.animation();

        assertTrue(visualElement.getForm().length == visualElement.getColorPallet().length);

        assertEquals(5, visualElement.getForm().length);
        assertEquals('>', visualElement.getForm()[0]);
        assertEquals('-', visualElement.getForm()[1]);
        assertEquals('§', visualElement.getForm()[2]);
        assertEquals('-', visualElement.getForm()[3]);
        assertEquals('-', visualElement.getForm()[4]);
    }
}
