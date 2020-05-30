package com.lpoo.g72.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {
    private static CommandInvoker instance;
    private List<Command> commands;

    private CommandInvoker() {
        this.commands = new ArrayList<>();
    }

    public static CommandInvoker getInstance() {
        if (instance == null) {
            instance = new CommandInvoker();
        }
        return instance;
    }

    public void executeCommands() {
        for (Command command : this.commands)
            command.execute();
        this.commands.clear();
    }

    public void addCommand(Command command) {
        this.commands.add(command);
    }
}
