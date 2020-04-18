import java.io.IOException;

public class ArenaController{
    private final ArenaView gui;
    private final Arena arena;

    public ArenaController(ArenaView gui, Arena arena) {
        this.gui = gui;
        this.arena = arena;

        arena.addObserver(gui);
    }

    public void init() throws IOException {
        gui.draw();
    }
}

