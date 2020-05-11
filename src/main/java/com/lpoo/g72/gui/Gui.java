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
import java.util.ArrayList;
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

        this.scene.draw(this.graphics, monsters);

        this.drawScoreBar(destroyedBlocks, score, helicopter);

        this.visualHelicopter.draw(this.graphics,helicopter);
    }

    private void drawScoreBar(int destroyedBlocks, int score, Helicopter helicopter) {
        this.graphics.setForegroundColor(TextColor.Factory.fromString("#ce0000"));

        this.graphics.drawLine(0, this.height - 4, this.width, this.height - 4, '▘');

        int sceneBlocks = this.scene.getSceneBlocks();
        destroyedBlocks = Math.min(destroyedBlocks,sceneBlocks);

        this.graphics.setForegroundColor(TextColor.Factory.fromString("#2a2a2a"));
        this.graphics.putString(5, this.height - 3, "Blocks: " + destroyedBlocks + "/" + sceneBlocks);
        this.graphics.putString(22, this.height - 3, "City: " + this.scene.getName());
        this.graphics.putString(39, this.height - 3, "Lives: " + helicopter.getLives());
        this.graphics.putString(this.width - 48, this.height - 3, "Missiles: " + helicopter.unusedVerticalMissiles());
        this.graphics.putString(this.width - 31, this.height - 3, "Bombs: " + helicopter.unusedHorizontalMissiles());
        this.graphics.putString(this.width - 17, this.height - 3, "Score: " + score);
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

    public void drawMessage(List<String> message) {
        this.graphics.setForegroundColor(TextColor.Factory.fromString("#b10000"));

        for(int i = 0; i< message.size();i++){
            this.graphics.putString((this.width-message.get(i).length()-2) / 2, this.height/4 + i, message.get(i));
        }
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

    public List<String> getGameOverMessage(){
        List<String> gameOver = new ArrayList<>();
        gameOver.add(" ██████╗  █████╗ ███╗   ███╗███████╗     ██████╗ ██╗   ██╗███████╗██████╗ \n");
        gameOver.add("██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██╔═══██╗██║   ██║██╔════╝██╔══██╗\n");
        gameOver.add("██║  ███╗███████║██╔████╔██║█████╗      ██║   ██║██║   ██║█████╗  ██████╔╝\n");
        gameOver.add("██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗\n");
        gameOver.add("╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║\n");
        gameOver.add(" ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝\n");
        return gameOver;
    }
}