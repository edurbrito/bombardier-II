package com.lpoo.g72.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HighscoreController {

    private Map<String, List<Integer>> highscores;

    public HighscoreController() {
        this.highscores = new HashMap<>();
        this.read();
    }

    public void read() {

        try {

            File file = new File("src/main/java/com/lpoo/g72/highscores.json");
            boolean newFile = file.createNewFile();

            if (!newFile) {
                Type type = new TypeToken<Map<String, List<Integer>>>() {
                }.getType();
                this.setHighscores(new Gson().fromJson(new FileReader(file), type));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write() {
        try {
            Writer writer = new FileWriter("src/main/java/com/lpoo/g72/highscores.json");

            new Gson().toJson(this.highscores, writer);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addScore(String scene, int score) {

        this.highscores.get(scene).add(score);

        for (Map.Entry<String, List<Integer>> entry : this.highscores.entrySet()) {
            entry.getValue().sort(Collections.reverseOrder());
            while (entry.getValue().size() > 5) {
                entry.getValue().remove(entry.getValue().size() - 1);
            }
        }
    }

    public Map<String, List<Integer>> getHighscores() {
        return this.highscores;
    }

    public void setHighscores(Map<String, List<Integer>> highscores) {
        this.highscores = highscores;
    }
}