package com.lpoo.g72.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class HighscoreControllerTest {

    HighscoreController highscoreController;

    @Before
    public void init() {
        highscoreController = Mockito.spy(new HighscoreController());
    }

    @Test
    public void testWrite() {
        highscoreController.read();
        Type type = new TypeToken<Map<String, List<Integer>>>() {
        }.getType();

        Map<String, List<Integer>> oldMap = new HashMap<>();
        if (highscoreController.getHighscores() != null) {
            oldMap.putAll(new Gson().fromJson(highscoreController.getHighscores().toString(), type));
            Mockito.verify(highscoreController, Mockito.times(1)).read();
            Mockito.verify(highscoreController, Mockito.times(1)).setHighscores(Mockito.any());
        } else
            highscoreController.setHighscores(new HashMap<>());

        assertNotNull(highscoreController.getHighscores());

        Map<String, List<Integer>> map = highscoreController.getHighscores();
        if (map.get("OPorto") != null) {
            map.get("OPorto").clear();

            map.get("OPorto").add(1234);
            map.get("OPorto").add(1235);
            map.get("OPorto").add(1236);
            map.get("OPorto").add(1237);

            map.get("Lisbon").clear();
            map.get("Random").clear();

            highscoreController.addScore("Lisbon", 12);
            highscoreController.addScore("Lisbon", 18);
            highscoreController.addScore("Lisbon", 15);
            highscoreController.addScore("Lisbon", 13);
            highscoreController.addScore("Lisbon", 14);
            highscoreController.addScore("Lisbon", 17);
            highscoreController.addScore("Random", 222);
            highscoreController.addScore("Random", 10201);
            assertEquals(4, highscoreController.getHighscores().get("OPorto").size());
            assertEquals(5, highscoreController.getHighscores().get("Lisbon").size());
            assertEquals(2, highscoreController.getHighscores().get("Random").size());

            for (Map.Entry<String, List<Integer>> entry : highscoreController.getHighscores().entrySet()) {
                int max = entry.getValue().get(0);

                for (int i : entry.getValue()) {
                    assertTrue(i <= max);
                }
            }
        }

        highscoreController.write();

        highscoreController.read();

        if (map.get("OPorto") != null) {
            assertEquals(4, highscoreController.getHighscores().get("OPorto").size());
            assertEquals(5, highscoreController.getHighscores().get("Lisbon").size());
            assertEquals(2, highscoreController.getHighscores().get("Random").size());

            for (Map.Entry<String, List<Integer>> entry : highscoreController.getHighscores().entrySet()) {
                int max = entry.getValue().get(0);

                for (int i : entry.getValue()) {
                    assertTrue(i <= max);
                }
            }
        }

        highscoreController.setHighscores(oldMap);

        highscoreController.write();
    }

    @Test
    public void testRead() {

        assertNotNull(highscoreController.getHighscores());

        if (highscoreController.getHighscores().size() > 0) {

            assertEquals(3, highscoreController.getHighscores().size());

            assertTrue(highscoreController.getHighscores().get("OPorto").size() > 0);
            assertTrue(highscoreController.getHighscores().get("Lisbon").size() > 0);
            assertTrue(highscoreController.getHighscores().get("Random").size() > 0);
            assertNull(highscoreController.getHighscores().get("Other"));
        }

    }
}
