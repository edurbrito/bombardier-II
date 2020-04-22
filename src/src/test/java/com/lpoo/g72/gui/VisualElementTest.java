package com.lpoo.g72.gui;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.scene.Element;
import com.lpoo.g72.scene.Helicopter;
import com.lpoo.g72.scene.Position;
import org.junit.Test;
import org.mockito.Mockito;

public class VisualElementTest {

    class PositionStub extends Position{
        int xVal = 10, yVal = 15;

        public PositionStub(int x, int y) {
            super(x, y);
        }

        public int getX() {
            return xVal;
        }

        public int getY() {
            return yVal;
        }
    }

    @Test
    public void testDraw(){
        Element helicopter = Mockito.mock(Helicopter.class);
        Mockito.when(helicopter.getPosition()).thenReturn(new PositionStub(10,15));

        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        VisualElement visualHelicopter = new VisualElement("hhh", "#000000");
        visualHelicopter.draw(graphics,helicopter.getPosition());


        Mockito.verify(graphics,Mockito.times(1)).putString(10,15,"hhh");
    }
}
