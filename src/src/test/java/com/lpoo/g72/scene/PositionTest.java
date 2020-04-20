package com.lpoo.g72.scene;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PositionTest {

    @Test
    public void getPosition(){
        Position pos1 = new Position(20,34);
        Position pos2 = new Position(6,0);

        assertEquals(20, pos1.getX());
        assertEquals(34, pos1.getY());

        assertEquals(6, pos2.getX());
        assertEquals(0, pos2.getY());
    }

    @Test
    public void getLeft(){
        Position pos1 = new Position(20,34);
        Position pos2 = new Position(6,0);

        Position left = pos1.left();
        assertEquals(19, left.getX());
        assertEquals(34, left.getY());
        assertEquals(20, pos1.getX());
        assertEquals(34, pos1.getY());

        left = pos2.left();
        assertEquals(5, left.getX());
        assertEquals(0, left.getY());
        assertEquals(6, pos2.getX());
        assertEquals(0, pos2.getY());
    }

    @Test
    public void getRight(){
        Position pos1 = new Position(20,34);
        Position pos2 = new Position(6,0);

        Position right = pos1.right();
        assertEquals(21, right.getX());
        assertEquals(34, right.getY());
        assertEquals(20, pos1.getX());
        assertEquals(34, pos1.getY());

        right = pos2.right();
        assertEquals(7, right.getX());
        assertEquals(0, right.getY());
        assertEquals(6, pos2.getX());
        assertEquals(0, pos2.getY());
    }

    @Test
    public void getUp(){
        Position pos1 = new Position(20,34);
        Position pos2 = new Position(6,0);

        Position up = pos1.up();
        assertEquals(20, up.getX());
        assertEquals(33, up.getY());
        assertEquals(20, pos1.getX());
        assertEquals(34, pos1.getY());

        up = pos2.up();
        assertEquals(6, up.getX());
        assertEquals(-1, up.getY());
        assertEquals(6, pos2.getX());
        assertEquals(0, pos2.getY());
    }

    @Test
    public void getDown(){
        Position pos1 = new Position(20,34);
        Position pos2 = new Position(6,0);

        Position down = pos1.down();
        assertEquals(20, down.getX());
        assertEquals(35, down.getY());
        assertEquals(20, pos1.getX());
        assertEquals(34, pos1.getY());

        down = pos2.down();
        assertEquals(6, down.getX());
        assertEquals(1, down.getY());
        assertEquals(6, pos2.getX());
        assertEquals(0, pos2.getY());
    }

    @Test
    public void equalPosition(){
        Position pos1 = new Position(20,34);
        Position pos2 = new Position(20,34);

        assertEquals(true, pos1.equals(pos2));
    }
}
