package com.lpoo.g72.gui;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo.g72.scene.Helicopter;
import com.lpoo.g72.scene.Position;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class VisualHelicopterTest {

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

    public static class VisualHelicopterStub extends VisualHelicopter {

        public VisualHelicopterStub() {
            this.wing = WING.NORMAL;
        }

        public void draw(TextGraphics graphics, Position position){
            if(wing != WING.LAUNCHING){
                graphics.setCharacter(position.getX(), position.getY(),'\\');
            }
            else{
                graphics.setCharacter(position.getX(), position.getY(),'/');
            }

            graphics.setCharacter(position.getX()+1, position.getY(),'-');

            graphics.setCharacter(position.getX()+2, position.getY(),'O');
        }

    }

    @Test
    public void testDrawNormal(){
        Helicopter helicopter = Mockito.mock(Helicopter.class);
        Mockito.when(helicopter.getPosition()).thenReturn(new PositionStub(10,15));

        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        VisualHelicopterStub visualHelicopter = new VisualHelicopterStub();
        visualHelicopter.draw(graphics,helicopter.getPosition());

        Mockito.verify(graphics,Mockito.times(1)).setCharacter(10,15,'\\');
        Mockito.verify(graphics,Mockito.times(1)).setCharacter(11,15,'-');
        Mockito.verify(graphics,Mockito.times(1)).setCharacter(12,15,'O');
    }

    @Test
    public void testDrawWingAcessors(){
        Helicopter helicopter = Mockito.mock(Helicopter.class);
        Mockito.when(helicopter.getPosition()).thenReturn(new PositionStub(10,15));

        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        VisualHelicopter visualHelicopter = new VisualHelicopter();
        visualHelicopter.draw(graphics,helicopter.getPosition());

        assertEquals(true, visualHelicopter.getWing() == VisualHelicopter.WING.NORMAL);
        visualHelicopter.setWing(VisualHelicopter.WING.LAUNCHING);
        assertEquals(true, visualHelicopter.getWing() == VisualHelicopter.WING.LAUNCHING);
    }

    @Test
    public void testDrawLaunching(){
        Helicopter helicopter = Mockito.mock(Helicopter.class);
        Mockito.when(helicopter.getPosition()).thenReturn(new PositionStub(10,15));

        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        VisualHelicopterStub visualHelicopter = new VisualHelicopterStub();
        visualHelicopter.setWing(VisualHelicopter.WING.LAUNCHING);

        visualHelicopter.draw(graphics,helicopter.getPosition());

        Mockito.verify(graphics,Mockito.times(1)).setCharacter(10,15,'/');
        Mockito.verify(graphics,Mockito.times(1)).setCharacter(11,15,'-');
        Mockito.verify(graphics,Mockito.times(1)).setCharacter(12,15,'O');
    }

}
