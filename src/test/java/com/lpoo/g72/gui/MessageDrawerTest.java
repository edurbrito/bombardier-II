package com.lpoo.g72.gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MessageDrawerTest {
    MessageDrawer messageDrawer;
    TextGraphics graphics;

    @Before
    public void init() {
        this.graphics = Mockito.mock(TextGraphics.class);
        this.messageDrawer = new MessageDrawer(100, 50, this.graphics);
    }

    @Test
    public void testDrawMessage() {

        List<List<String>> messages = new ArrayList<>();

        messages.add(this.messageDrawer.getGameTitle());
        messages.add(this.messageDrawer.getHighscoresMessage());
        messages.add(this.messageDrawer.getVictoryMessage());
        messages.add(this.messageDrawer.getGameOverMessage());
        messages.add(this.messageDrawer.getNewRoundMessage());

        for (List<String> message : messages) {
            this.messageDrawer.drawMessage(message, "#FFFFFF", "something");
            Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(Mockito.any());
            Mockito.clearInvocations(graphics);
        }
    }

}
