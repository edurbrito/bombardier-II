package com.lpoo.g72.command;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class QuitCommand implements Command{
    private final TerminalScreen screen;

    public QuitCommand(TerminalScreen screen){
        this.screen = screen;
    }

    @Override
    public void execute() {
        try {
            screen.close();
        } catch (IOException e) {}
    }
}
