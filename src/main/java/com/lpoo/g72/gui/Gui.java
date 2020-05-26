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
import java.util.Map;

public class Gui {

    private final TerminalScreen screen;
    private Scene scene;

    private final int width;
    private final int height;
    private TextGraphics graphics;
    private final MessageDrawer messageDrawer;

    public enum Key {UP, DOWN, SPACE, RIGHT, QUIT, ENTER, NULL}

    public Gui(int width, int height) throws IOException {
        this.width = width;
        this.height = height;

        this.screen = createTerminalScreen();
        this.setScreenProperties();

        this.messageDrawer = new MessageDrawer(width,height, this.graphics);
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

    public void drawScene(Helicopter helicopter, List<Monster> monsters, int destroyedBlocks, int score) {

        this.graphics.setBackgroundColor(TextColor.Factory.fromString("#cccccc"));
        this.graphics.setForegroundColor(TextColor.Factory.fromString("#cccccc"));
        this.graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(this.width, this.height),
                ' '
        );

        this.scene.draw(this.graphics, helicopter, monsters);

        this.drawScoreBar(destroyedBlocks, score, helicopter);

    }

    private void drawScoreBar(int destroyedBlocks, int score, Helicopter helicopter) {
        this.graphics.setForegroundColor(TextColor.Factory.fromString("#ce0000"));

        this.graphics.drawLine(0, this.height - 4, this.width, this.height - 4, '▘');

        int sceneBlocks = this.scene.getSceneBlocks();
        destroyedBlocks = Math.min(destroyedBlocks,sceneBlocks);

        this.graphics.setForegroundColor(TextColor.Factory.fromString("#2a2a2a"));
        this.graphics.putString(5, this.height - 3, "Blocks: " + destroyedBlocks + "/" + sceneBlocks);
        this.graphics.putString(24, this.height - 3, "City: " + this.scene.getName());
        this.graphics.putString(39, this.height - 3, "Lives: " + helicopter.getLives());
        this.graphics.putString(this.width - 48, this.height - 3, "Missiles: " + helicopter.unusedHorizontalMissiles());
        this.graphics.putString(this.width - 31, this.height - 3, "Bombs: " + helicopter.unusedVerticalMissiles());
        this.graphics.putString(this.width - 17, this.height - 3, "Score: " + score);
    }

    public void drawMenu(int selected, List<String> menuOptions){
        String color1 =  "#00009f";
        String color2 =  "#191919";

        this.graphics.setBackgroundColor(TextColor.Factory.fromString("#cccccc"));
        this.graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(this.width, this.height),
                ' '
        );

        this.graphics.enableModifiers(SGR.BOLD);
        this.messageDrawer.drawMessage(this.messageDrawer.getGameTitle(), color1, "THE REVENGE OF THE SKYSCRAPPERS");

        String s;
        for(int i = 0; i < menuOptions.size(); i++){
            if(i == selected){
                this.graphics.setForegroundColor(TextColor.Factory.fromString(color2));
                s = ">>  " + menuOptions.get(i).toUpperCase() + "  <<";
            }
            else{
                this.graphics.setForegroundColor(TextColor.Factory.fromString(color1));
                s = menuOptions.get(i).toUpperCase();
            }
            this.graphics.putString((this.width-s.length()) / 2, this.height/2 + 2 +3*i, s);
        }

        this.drawControls();
    }

    public void drawHighscores(Map<String, List<Integer>> highscores){
        String color1 =  "#9f395d";
        String color2 = "#191919";

        this.graphics.setBackgroundColor(TextColor.Factory.fromString("#cccccc"));
        this.graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(this.width, this.height),
                ' '
        );

        this.graphics.enableModifiers(SGR.BOLD);
        List<String> title = this.messageDrawer.getHighscoresMessage();
        this.messageDrawer.drawMessage( title, color1,"");

        int i = 0, j;
        String s;
        for(Map.Entry<String, List<Integer>> entry : highscores.entrySet()) {
            this.graphics.setForegroundColor(TextColor.Factory.fromString(color1));
            s = entry.getKey() + " Scene";
            this.graphics.fillRectangle(
                    new TerminalPosition(this.width/ 4 -2 + i, this.height/4 + title.size() + 3),
                    new TerminalSize(s.length(), 3),
                    '█'
            );
            this.graphics.putString(this.width / 4 - 2 + i, this.height/4 + title.size() + 4, s.toUpperCase());

            j = 0;
            this.graphics.setForegroundColor(TextColor.Factory.fromString(color2));
            for(Integer score : entry.getValue()){
                this.graphics.putString(this.width / 4 + 2 + i, this.height/4 + 3 + j + title.size() + 4, score.toString());
                j += 2;
            }
            i += 20;
        }

        this.graphics.setForegroundColor(TextColor.Factory.fromString(color1));
        this.graphics.putString(this.width - 15 , this.height- 4,"Q");
        this.graphics.setForegroundColor(TextColor.Factory.fromString(color2));
        this.graphics.putString(this.width - 12, this.height- 4 ,"Back");
    }

    public void drawControls(){
        String[] controls = {"▲", "▼", "▶", "SPACE BAR", "Q"};
        String[] description = {"Move Up","Move Down","Shoot", "Drop Bomb", "Quit"};

        int y = 7;
        for(int i = 0; i< controls.length; i++){
            this.graphics.setForegroundColor(TextColor.Factory.fromString("#00009f"));
            this.graphics.putString(y , this.height- 4,controls[i]);
            y += controls[i].length() + 2;
            this.graphics.setForegroundColor(TextColor.Factory.fromString("#333333"));
            this.graphics.putString(y, this.height- 4 ,description[i]);
            y += 7 + description[i].length();
        }

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
            if((input.getKeyType() == KeyType.Enter))
                return Key.ENTER;
            if (input.getKeyType() == KeyType.EOF || (input.getKeyType() == KeyType.Character && input.getCharacter() == 'q'))
                return Key.QUIT;
        } catch (NullPointerException n) {
            return Key.NULL;
        }

        return Key.NULL;
    }

    public void refreshScreen() throws IOException {
        this.screen.refresh();
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

    public MessageDrawer getMessageDrawer() {
        return messageDrawer;
    }
}