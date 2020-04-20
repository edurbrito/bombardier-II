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

    public void updateView() throws IOException {

        Command cmd = gui.getCommand();
        cmd.execute(scene.getHelicopter());

        gui.drawScene(scene);
    }

    public void execute() throws IOException {
        gui.drawScene(scene);

        Instant start = Instant.now(), current;
        Duration duration;
        long interval = 200000000;

        while(true){
            current = Instant.now();
            duration = Duration.between(start , current);

            if(newRound()){
                decreaseAltitude(scene.getHelicopter());
            }

            if(duration.getNano() >= interval){
                moveHelicopterForward();
                start = current;
            }

            updateView();
        }
    }

    boolean newRound(){
        return scene.getHelicopter().getPosition().getX() == scene.getWidth()-1;
    }

    void moveHelicopterForward(){
        Command right = new RightCommand();
        right.execute(scene.getHelicopter());
    }

    void decreaseAltitude(Element element){
        int y = element.getPosition().getY() + 1;
        element.setPosition(new Position(0,y));
    }
}
