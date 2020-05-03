package com.lpoo.g72.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class CommandInvoker {
    private List<Command> commands;
    private static CommandInvoker instance;

    private CommandInvoker() {
        commands = new ArrayList<>();
    }

    public static CommandInvoker getInstance() {
        if (instance == null) {
            instance = new CommandInvoker();
        }
        return instance;
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
