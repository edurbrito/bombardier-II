package com.lpoo.g72.gui;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.lpoo.g72.gui.visualElement.VisualElement;
import com.lpoo.g72.gui.visualElement.VisualHelicopter;
import com.lpoo.g72.gui.visualElement.VisualHorizontalMissile;
import com.lpoo.g72.gui.visualElement.VisualVerticalMissile;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Missile;
import com.lpoo.g72.model.element.Monster;

import java.io.IOException;
import java.util.List;

public class Gui {

    private final TerminalScreen screen;
    private final VisualHelicopter visualHelicopter;

    private VisualElement[] visualMissiles;
    private Scene scene;

    private final int width;
    private final int height;

    public enum Key {UP, DOWN, SPACE, RIGHT, QUIT, NULL};

    public Gui(int width, int height) throws IOException {
        this.width = width;
        this.height = height;

        this.visualHelicopter = new VisualHelicopter();
        this.screen = createTerminalScreen();
        this.setScreenProperties();

        this.visualMissiles = new VisualElement[]{new VisualVerticalMissile(), new VisualHorizontalMissile()};
    }

    private TerminalScreen createTerminalScreen() throws IOException {
        TerminalSize terminalSize = new TerminalSize(this.width, this.height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        return new TerminalScreen(terminal);
    }

    private void setScreenProperties() throws IOException {
        this.screen.setCursorPosition(null);
        this.screen.startScreen();
        this.screen.doResizeIfNecessary();
    }

    public void draw(Helicopter helicopter, List<Monster> monsters, List<Missile> verticalMissiles, List<Missile> horizontalMissiles) {
        this.screen.clear();

        TextGraphics graphics = this.screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#C0C0C0"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(width, height),
                ' '
        );

        this.scene.draw(graphics, monsters);

        this.drawVisualMissiles(graphics,verticalMissiles,horizontalMissiles);

        this.visualHelicopter.draw(graphics,helicopter);
    }

    public void drawVisualMissiles(TextGraphics graphics, List<Missile> verticalMissiles, List<Missile> horizontalMissiles){

        for(int i = 0; i < verticalMissiles.size(); i++){
            this.visualMissiles[0].draw(graphics, verticalMissiles.get(i));
        }

        for(int i = 0; i < horizontalMissiles.size(); i++){
            this.visualMissiles[1].draw(graphics, horizontalMissiles.get(i));
        }
    }

    public int verticalMissileSize() {
        return this.visualMissiles[0].getForm().length;
    }

    public int horizontalMissileSize() {
        return this.visualMissiles[1].getForm().length;
    }

    public void refreshScreen() throws IOException {
        this.screen.refresh();
    }

    public Key getKey() throws IOException {
        try {
            KeyStroke input = this.screen.pollInput();

            if (input.getKeyType() == KeyType.ArrowDown)
                return Key.DOWN;
            if (input.getKeyType() == KeyType.ArrowUp)
                return Key.UP;
            if (input.getKeyType() == KeyType.ArrowRight)
                return Key.RIGHT;
            if ((input.getKeyType() == KeyType.Character && input.getCharacter() == ' '))
                return Key.SPACE;
            if (input.getKeyType() == KeyType.EOF || (input.getKeyType() == KeyType.Character && input.getCharacter() == 'q'))
                return Key.QUIT;
        } catch (NullPointerException n) {
            return Key.NULL;
        }

        return Key.NULL;
    }

    public boolean removedBuilding(int x, int y) {
        return this.scene.removedBuilding(x,y);
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

        for (char character : gameOver.toCharArray()) {
            graphics.putString(new TerminalPosition(start++, height), String.valueOf(character));
        }

        screen.refresh();
    }

    public void closeScreen() throws IOException {
        this.screen.close();
    }

    public TerminalScreen getScreen() {
        return this.screen;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return this.scene;
    }

    public VisualHelicopter getVisualHelicopter() {
        return this.visualHelicopter;
    }

}