import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class ArenaView implements ArenaObserver{
    private final TerminalScreen screen;
    private int width;
    private int height;

    public ArenaView(int width, int height) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        this.width = width;
        this.height = height;
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    public void draw() throws IOException {
        screen.clear();
        drawArena();
        screen.refresh();
    }

    private void drawArena() {
        System.out.println("here");
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0),new TerminalSize(width, height),' ');
    }
}
