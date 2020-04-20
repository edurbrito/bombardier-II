package com.lpoo.g72.gui;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.lpoo.g72.controller.Command;
import com.lpoo.g72.controller.DoNothingCommand;
import com.lpoo.g72.controller.DownCommand;
import com.lpoo.g72.controller.UpCommand;
import com.lpoo.g72.scene.*;

import java.io.IOException;

public class Gui{
    private final TerminalScreen screen;
    private final int width;
    private final int height;

    public enum Key{UP,DOWN,NULL};

    public Gui(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.screen = createTerminalScreen();
        setScreenProperties();
    }

    private TerminalScreen createTerminalScreen() throws IOException {
        TerminalSize terminalSize = new TerminalSize(this.width, this.height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        return new TerminalScreen(terminal);
    }

    private void setScreenProperties() throws IOException {
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void drawSceneBuildings(TextGraphics graphics, Scene scene){
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#2a2a2a"));

        int height = scene.getHeight();
        int width = scene.getWidth();
        char[][] buildings = scene.getBuildings();

        for(int h = 0; h < height; ++h){
            for(int w = 0; w < width; ++w){
                graphics.putString( width - w - 1, height - h - 5, String.valueOf(buildings[h][w]));
            }
        }
    }

    public void drawMenu() throws IOException {
        screen.clear();

        TextGraphics graphics = this.screen.newTextGraphics();

        graphics.setBackgroundColor(TextColor.Factory.fromString("#A0A0A0"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(this.width, this.height),
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
    }

    public void drawScene(Scene scene) throws IOException {
        screen.clear();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#C0C0C0"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(scene.getWidth(), scene.getHeight()),
                ' '
        );

        this.drawScoreBar(graphics,scene);

        this.drawSceneBuildings(graphics,scene);

        this.drawElement(scene.getHelicopter());

        screen.refresh();
    }

    private void drawScoreBar(TextGraphics graphics, Scene scene){
        graphics.setForegroundColor(TextColor.Factory.fromString("#e60000"));
        graphics.drawLine(0, scene.getHeight()-4, 8, scene.getHeight()-4,'=');

        graphics.drawLine(scene.getWidth() - 9, scene.getHeight()-4, scene.getWidth(), scene.getHeight()-4,'=');

        graphics.setForegroundColor(TextColor.Factory.fromString("#2a2a2a"));
        graphics.putString(10, scene.getHeight()-4,"Blocks: ");
        graphics.putString(30, scene.getHeight()-4,"City: ");
        graphics.putString(scene.getWidth()-45, scene.getHeight()-4,"Score: ");
        graphics.putString(scene.getWidth()-20, scene.getHeight()-4,"Lives: ");
    }

    private void drawElement(Element element) {
        drawCharacter(element.getPosition(), element.getStr(), element.getColor());
    }

    private void drawCharacter(Position position, String character, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.enableModifiers(SGR.BOLD);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#C0C0C0"));
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(position.getX(), position.getY(), character);
    }

    public Key getKey() throws IOException {
        try{
            KeyStroke input = screen.pollInput();

            if (input.getKeyType() == KeyType.ArrowDown)
                return Key.DOWN;
            if (input.getKeyType() == KeyType.ArrowUp)
                return Key.UP;
        }
        catch(NullPointerException n){
            return Key.NULL;
        }

        return Key.NULL;
    }


}