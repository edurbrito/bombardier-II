package com.lpoo.g72.gui;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.lpoo.g72.scene.Scene;

import java.io.IOException;

public class Gui {
    private Scene scene;
    private final TerminalScreen screen;

    private final int fixedHeight;
    private final int fixedWidth;

    public final boolean mainMenu() throws IOException {

        // TODO: Choose city to attack

        screen.clear();

        TextGraphics graphics = this.screen.newTextGraphics();

        graphics.setBackgroundColor(TextColor.Factory.fromString("#A0A0A0"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(this.fixedWidth, this.fixedHeight),
                ' '
        );

        String gameOver = "Main Menu";
        int start = screen.getTerminalSize().getColumns() / 2 - 5;
        int height = screen.getTerminalSize().getRows() / 4;

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.enableModifiers(SGR.BOLD);

        for( char character : gameOver.toCharArray()) {
            graphics.putString(new TerminalPosition(start++,height), String.valueOf(character));
        }

        screen.refresh();

        return true;
    }

    public Gui(int fixedWidth, int fixedHeight) throws IOException {
        this.fixedWidth = fixedWidth;
        this.fixedHeight = fixedHeight;
        TerminalSize terminalSize = new TerminalSize(this.fixedWidth, this.fixedHeight);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    public void setScene(Scene scene){
        this.scene = scene;
    }

    public void draw() throws IOException {
        screen.clear();
        this.drawScene();
        screen.refresh();
    }

    private void drawScene() {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#A0A0A0"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(scene.getWidth(), scene.getHeight()),
                ' '
        );
    }

    public int getFixedHeight() {
        return fixedHeight;
    }

    public int getFixedWidth() {
        return fixedWidth;
    }
}