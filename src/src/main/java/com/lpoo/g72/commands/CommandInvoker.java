package com.lpoo.g72.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class CommandInvoker {
    private List<Command> commands;

    private CommandInvoker() {
        commands = new ArrayList<>();
    }

    private static class CommandInvokerHolder{
        private static final CommandInvoker INSTANCE = new CommandInvoker();
    }

    public static CommandInvoker getInstance() {
        return CommandInvokerHolder.INSTANCE;
    }

    public void executeCommands(){
        for(Command command: commands)
            command.execute();
        commands.clear();
    }

    public void addCommand(Command command){
        this.commands.add(command);
    }
}
