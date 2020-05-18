package com.lpoo.g72.command;

import com.lpoo.g72.commands.Command;
import com.lpoo.g72.commands.CommandInvoker;
import com.lpoo.g72.commands.DropMissile;
import com.lpoo.g72.commands.ShootMissile;
import com.lpoo.g72.commands.directional.DownCommand;
import com.lpoo.g72.commands.directional.LeftCommand;
import com.lpoo.g72.commands.directional.RightCommand;
import com.lpoo.g72.commands.directional.UpCommand;
import com.lpoo.g72.model.ElementStub;
import com.lpoo.g72.model.Position;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Missile;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class CommandTest {

    ElementStub elementStub;
    List<Command> commandList;

    @Before
    public void commandInit(){

        Position position = new Position(12,24);
        elementStub = Mockito.spy(new ElementStub(position));
        commandList = new ArrayList<>();

        DownCommand downCommand = Mockito.spy(new DownCommand(elementStub));
        UpCommand upCommand = Mockito.spy(new UpCommand(elementStub));
        RightCommand rightCommand = Mockito.spy(new RightCommand(elementStub));
        LeftCommand leftCommand = Mockito.spy(new LeftCommand(elementStub));

        commandList.add(downCommand);
        commandList.add(upCommand);
        commandList.add(rightCommand);
        commandList.add(leftCommand);
    }

    @Test
    public void testDirectionalCommands(){

        Position position = elementStub.getPosition();

        for(Command command : commandList){

            Mockito.clearInvocations(elementStub);

            command.execute();

            Mockito.verify(command, Mockito.times(1)).execute();

            Mockito.verify(elementStub,Mockito.times(1)).getPosition();

            Mockito.verify(elementStub,Mockito.times(1)).setPosition(Mockito.any());

        }

        assertEquals(position.down().up().right().left(),elementStub.getPosition());
    }

    @Test
    public void testCommandInvoker(){
        CommandInvoker commandInvoker = Mockito.spy(CommandInvoker.getInstance());

        assertNotNull(commandInvoker);

        for(Command command : commandList){
            commandInvoker.addCommand(command);
        }

        Position pbefore = new Position(elementStub.getX(),elementStub.getY()).down().up().right().left();

        commandInvoker.executeCommands();

        assertTrue(pbefore.equals(elementStub.getPosition()));

        for(Command command : commandList){
            Mockito.verify(command,Mockito.times(1)).execute();
        }

        commandInvoker.addCommand(new DownCommand(elementStub));

        pbefore = new Position(elementStub.getX(),elementStub.getY()).down();

        commandInvoker.executeCommands();

        assertTrue(pbefore.equals(elementStub.getPosition()));

        commandInvoker.executeCommands();

        assertTrue(pbefore.equals(elementStub.getPosition()));
    }

    @Test
    public void testDropMissiles(){
        Helicopter helicopter = Mockito.spy(new Helicopter(new Position(0,1),5,5));
        DropMissile dropMissile = new DropMissile(helicopter);

        dropMissile.execute();
        Mockito.verify(helicopter,Mockito.times(1)).drop();

        assertEquals(1,helicopter.getVerticalMissiles().size());

        for(Missile missile : helicopter.getVerticalMissiles()){
            assertTrue(missile.getPosition().equals(helicopter.getPosition().down().left()));
        }

        assertEquals(4,helicopter.unusedVerticalMissiles());

        dropMissile.execute();
        dropMissile.execute();

        assertEquals(3,helicopter.getVerticalMissiles().size());

        for(Missile missile : helicopter.getVerticalMissiles()){
            assertTrue(missile.getPosition().equals(helicopter.getPosition().down().left()));
        }

        assertEquals(2,helicopter.unusedVerticalMissiles());

        dropMissile.execute();
        dropMissile.execute();

        assertEquals(5,helicopter.getVerticalMissiles().size());

        for(Missile missile : helicopter.getVerticalMissiles()){
            assertTrue(missile.getPosition().equals(helicopter.getPosition().down().left()));
        }

        assertEquals(0,helicopter.unusedVerticalMissiles());

        Mockito.verify(helicopter,Mockito.times(5)).drop();

        helicopter.resetMissiles();

        assertEquals(0,helicopter.getVerticalMissiles().size());

        for(Missile missile : helicopter.getVerticalMissiles()){
            assertTrue(missile.getPosition().equals(helicopter.getPosition().down().left()));
        }

        assertEquals(5,helicopter.unusedVerticalMissiles());

        dropMissile.execute();
        Mockito.verify(helicopter,Mockito.times(6)).drop();

        assertEquals(1,helicopter.getVerticalMissiles().size());

        for(Missile missile : helicopter.getVerticalMissiles()){
            assertTrue(missile.getPosition().equals(helicopter.getPosition().down().left()));
        }

        assertEquals(4,helicopter.unusedVerticalMissiles());

        for (Missile missile : helicopter.getVerticalMissiles()){
            missile.setExploded();
        }

        assertEquals(0,helicopter.getVerticalMissiles().size());

        for(Missile missile : helicopter.getVerticalMissiles()){
            assertTrue(missile.getPosition().equals(helicopter.getPosition().down().left()));
        }

        assertEquals(4,helicopter.unusedVerticalMissiles());

        dropMissile.execute();
        Mockito.verify(helicopter,Mockito.times(7)).drop();

        assertEquals(1,helicopter.getVerticalMissiles().size());

        for(Missile missile : helicopter.getVerticalMissiles()){
            assertTrue(missile.getPosition().equals(helicopter.getPosition().down().left()));
        }

        assertEquals(3,helicopter.unusedVerticalMissiles());
    }

    @Test
    public void testShootMissiles(){
        Helicopter helicopter = Mockito.spy(new Helicopter(new Position(0,1),5,5));
        ShootMissile shootMissile = new ShootMissile(helicopter);

        shootMissile.execute();
        Mockito.verify(helicopter,Mockito.times(1)).shoot();

        assertEquals(1,helicopter.getHorizontalMissiles().size());


        for(Missile missile : helicopter.getHorizontalMissiles()){
            assertTrue(missile.getPosition().equals(helicopter.getPosition().right().right()));
        }

        assertEquals(4,helicopter.unusedHorizontalMissiles());

        shootMissile.execute();
        shootMissile.execute();

        assertEquals(3,helicopter.getHorizontalMissiles().size());

        for(Missile missile : helicopter.getHorizontalMissiles()){
            assertTrue(missile.getPosition().equals(helicopter.getPosition().right().right()));
        }

        assertEquals(2,helicopter.unusedHorizontalMissiles());

        shootMissile.execute();
        shootMissile.execute();

        assertEquals(5,helicopter.getHorizontalMissiles().size());

        for(Missile missile : helicopter.getHorizontalMissiles()){
            assertTrue(missile.getPosition().equals(helicopter.getPosition().right().right()));
        }

        assertEquals(0,helicopter.unusedHorizontalMissiles());

        Mockito.verify(helicopter,Mockito.times(5)).shoot();

        helicopter.resetMissiles();

        assertEquals(0,helicopter.getHorizontalMissiles().size());

        for(Missile missile : helicopter.getHorizontalMissiles()){
            assertTrue(missile.getPosition().equals(helicopter.getPosition().right().right()));
        }

        assertEquals(5,helicopter.unusedHorizontalMissiles());

        shootMissile.execute();
        Mockito.verify(helicopter,Mockito.times(6)).shoot();

        assertEquals(1,helicopter.getHorizontalMissiles().size());

        for(Missile missile : helicopter.getHorizontalMissiles()){
            assertTrue(missile.getPosition().equals(helicopter.getPosition().right().right()));
        }

        assertEquals(4,helicopter.unusedHorizontalMissiles());

        for (Missile missile : helicopter.getHorizontalMissiles()){
            missile.setExploded();
        }

        assertEquals(0,helicopter.getHorizontalMissiles().size());

        for(Missile missile : helicopter.getHorizontalMissiles()){
            assertTrue(missile.getPosition().equals(helicopter.getPosition().right().right()));
        }

        assertEquals(4,helicopter.unusedHorizontalMissiles());

        shootMissile.execute();
        Mockito.verify(helicopter,Mockito.times(7)).shoot();

        assertEquals(1,helicopter.getHorizontalMissiles().size());

        for(Missile missile : helicopter.getHorizontalMissiles()){
            assertTrue(missile.getPosition().equals(helicopter.getPosition().right().right()));
        }

        assertEquals(3,helicopter.unusedHorizontalMissiles());
    }
}
