package com.lpoo.g72.controller;
import com.lpoo.g72.gui.Gui;
import com.lpoo.g72.scene.Element;
import com.lpoo.g72.scene.Helicopter;
import com.lpoo.g72.scene.Position;
import com.lpoo.g72.scene.Scene;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class SceneController{
    private final Gui gui;
    private Scene scene;

    public SceneController(Gui gui, Scene scene){
        this.gui = gui;
        this.scene = scene;
    }

    public void updateView(int previousAltitude) throws IOException {
        Command cmd;
        Gui.Key key = gui.getKey();

        if(key == Gui.Key.DOWN && isWithinDownLimit(previousAltitude)){
            cmd = new DownCommand(scene.getHelicopter());
            cmd.execute();
        }
        else if(key == Gui.Key.UP && isWithinUpLimit(previousAltitude)){
            cmd = new UpCommand(scene.getHelicopter());
            cmd.execute();
        }

        gui.drawScene(scene);
    }

    public void execute() throws IOException {
        gui.drawScene(scene);

        Instant start = Instant.now(), current;
        Duration duration;
        long interval = 200000000;

        int previousAltitude = scene.getHelicopter().getPosition().getY();

        while(true){
            current = Instant.now();
            duration = Duration.between(start , current);

            if(newRound()){
                decreaseAltitude(scene.getHelicopter(), previousAltitude);
                previousAltitude++;
            }

            if(duration.getNano() >= interval){
                moveHelicopterForward();
                start = current;
            }

            updateView(previousAltitude);
        }
    }

    boolean newRound(){
        return scene.getHelicopter().getPosition().getX() == scene.getWidth()-1;
    }

    boolean isWithinUpLimit(int previousAltitude){
        return scene.getHelicopter().getPosition().getY() - 1 > previousAltitude - 1;
    }

    boolean isWithinDownLimit(int previousAltitude){
        return scene.getHelicopter().getPosition().getY() - 1 < previousAltitude + 2;
    }

    void moveHelicopterForward(){
        Command right = new RightCommand(scene.getHelicopter());
        right.execute();
    }

    void decreaseAltitude(Element element, int previousAltitude){
        element.setPosition(new Position(0,previousAltitude + 1));
    }
}
