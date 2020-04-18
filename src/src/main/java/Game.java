import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        Arena arena = new Arena(200,200);
        ArenaView gui = new ArenaView(arena.getWidth(), arena.getHeight());
        ArenaController controller = new ArenaController(gui,arena);

        controller.init();
    }
}
