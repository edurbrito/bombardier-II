package com.lpoo.g72.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.lpoo.g72.gui.visualElement.VisualHelicopter;
import com.lpoo.g72.model.element.Helicopter;
import com.lpoo.g72.model.element.Monster;

import java.io.IOException;
import java.util.List;

public class Gui {

    private final TerminalScreen screen;
    private final VisualHelicopter visualHelicopter;
    private Scene scene;

    private final int width;
    private final int height;
    private TextGraphics graphics;

    public enum Key {UP, DOWN, SPACE, RIGHT, QUIT, NULL}

    public Gui(int width, int height) throws IOException {
        this.width = width;
        this.height = height;

        this.visualHelicopter = new VisualHelicopter();
        this.screen = createTerminalScreen();
        this.setScreenProperties();
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
        this.graphics = this.screen.newTextGraphics();
    }

    public void draw(Helicopter helicopter, List<Monster> monsters, int destroyedBlocks, int score) {

        this.graphics.setBackgroundColor(TextColor.Factory.fromString("#C0C0C0"));
        this.graphics.setForegroundColor(TextColor.Factory.fromString("#C0C0C0"));
        this.graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(this.width, this.height),
                ' '
        );

        this.scene.draw(graphics, monsters);

        this.drawScoreBar(graphics, destroyedBlocks, score, helicopter.getLives());

        this.visualHelicopter.draw(graphics,helicopter);
    }

    private void drawScoreBar(TextGraphics graphics, int destroyedBlocks, int score, int lives) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#e60000"));
        graphics.drawLine(0, this.height - 4, 8, this.height - 4, '=');
        graphics.drawLine(this.width - 9, this.height - 4, this.width, this.height - 4, '=');

        int sceneBlocks = this.scene.getSceneBlocks();
        destroyedBlocks = Math.min(destroyedBlocks,sceneBlocks);

        graphics.setForegroundColor(TextColor.Factory.fromString("#2a2a2a"));
        graphics.putString(10, this.height - 4, "Blocks: " + destroyedBlocks + "/" + sceneBlocks);
        graphics.putString(30, this.height - 4, "City: " + this.scene.getName());
        graphics.putString(this.width - 45, this.height - 4, "Score: " + score);
        graphics.putString(this.width - 20, this.height - 4, "Lives: " + lives);
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