package com.lpoo.g72.gui;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.lpoo.g72.scene.Scene;
import com.lpoo.g72.scene.SceneObserver;

import java.io.IOException;

public class Gui implements SceneObserver {
    private final TerminalScreen screen;
    private final int width;
    private final int height;

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

    public void drawSceneBuildings(TextGraphics graphics, Scene scene){
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

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
        graphics.setBackgroundColor(TextColor.Factory.fromString("#A0A0A0"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(scene.getWidth(), scene.getHeight()),
                ' '
        );

        this.drawSceneBuildings(graphics,scene);

        screen.refresh();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void sceneChanged(Scene scene) throws IOException {
        drawScene(scene);
    }
}