package com.lpoo.g72.command;

import com.googlecode.lanterna.screen.TerminalScreen;
import com.lpoo.g72.commands.Command;
import com.lpoo.g72.commands.NullCommand;
import com.lpoo.g72.commands.QuitCommand;
import com.lpoo.g72.commands.directional.DownCommand;
import com.lpoo.g72.commands.directional.LeftCommand;
import com.lpoo.g72.commands.directional.RightCommand;
import com.lpoo.g72.commands.directional.UpCommand;
import com.lpoo.g72.scene.ElementStub;
import com.lpoo.g72.scene.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

import java.io.IOException;
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
    public void testNonDirection() throws IOException {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);

        Mockito.doNothing().when(screen).close();

        QuitCommand quitCommand = Mockito.spy(new QuitCommand(screen));
        quitCommand.execute();

        Mockito.verify(quitCommand, Mockito.times(1)).execute();
        Mockito.verify(screen,Mockito.times(1)).close();

        NullCommand nullCommand = Mockito.spy(new NullCommand());
        nullCommand.execute();
        Mockito.verify(nullCommand, Mockito.times(1)).execute();
    }

}
