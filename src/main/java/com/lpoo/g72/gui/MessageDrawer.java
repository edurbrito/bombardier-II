package com.lpoo.g72.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class MessageDrawer {

    private final int width;
    private final int height;
    private TextGraphics graphics;

    public MessageDrawer(int width, int height, TextGraphics graphics) {
        this.width = width;
        this.height = height;
        this.graphics = graphics;
    }

    public void drawMessage(List<String> message, String hexColor, String additionalInfo) {
        this.graphics.setForegroundColor(TextColor.Factory.fromString(hexColor));

        for (int i = 0; i < message.size(); i++) {
            this.graphics.putString((this.width - message.get(i).length()) / 2, this.height / 4 + i, message.get(i));
        }

        if (!additionalInfo.isEmpty()) {
            this.graphics.fillRectangle(
                    new TerminalPosition((this.width - additionalInfo.length()) / 2, this.height / 4 + message.size() + 3),
                    new TerminalSize(additionalInfo.length(), 3),
                    '█'
            );
            this.graphics.putString((this.width - additionalInfo.length()) / 2, this.height / 4 + message.size() + 4, additionalInfo);
        }
    }

    public List<String> getGameOverMessage() {
        List<String> gameOver = new ArrayList<>();
        gameOver.add(" ██████╗  █████╗ ███╗   ███╗███████╗     ██████╗ ██╗   ██╗███████╗██████╗ \n");
        gameOver.add("██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██╔═══██╗██║   ██║██╔════╝██╔══██╗\n");
        gameOver.add("██║  ███╗███████║██╔████╔██║█████╗      ██║   ██║██║   ██║█████╗  ██████╔╝\n");
        gameOver.add("██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗\n");
        gameOver.add("╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║\n");
        gameOver.add(" ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝\n");
        return gameOver;
    }

    public List<String> getVictoryMessage() {
        List<String> gameOver = new ArrayList<>();
        gameOver.add("██╗   ██╗ ██████╗ ██╗   ██╗    ██╗    ██╗ ██████╗ ███╗   ██╗██╗\n");
        gameOver.add("╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║    ██║██╔═══██╗████╗  ██║██║\n");
        gameOver.add(" ╚████╔╝ ██║   ██║██║   ██║    ██║ █╗ ██║██║   ██║██╔██╗ ██║██║\n");
        gameOver.add("  ╚██╔╝  ██║   ██║██║   ██║    ██║███╗██║██║   ██║██║╚██╗██║╚═╝\n");
        gameOver.add("   ██║   ╚██████╔╝╚██████╔╝    ╚███╔███╔╝╚██████╔╝██║ ╚████║██╗\n");
        gameOver.add("   ╚═╝    ╚═════╝  ╚═════╝      ╚══╝╚══╝  ╚═════╝ ╚═╝  ╚═══╝╚═╝\n");
        return gameOver;
    }

    public List<String> getNewRoundMessage() {
        List<String> newRound = new ArrayList<>();
        newRound.add("███╗   ██╗███████╗██╗    ██╗    ██████╗  ██████╗ ██╗   ██╗███╗   ██╗██████╗ \n");
        newRound.add("████╗  ██║██╔════╝██║    ██║    ██╔══██╗██╔═══██╗██║   ██║████╗  ██║██╔══██╗\n");
        newRound.add("██╔██╗ ██║█████╗  ██║ █╗ ██║    ██████╔╝██║   ██║██║   ██║██╔██╗ ██║██║  ██║\n");
        newRound.add("██║╚██╗██║██╔══╝  ██║███╗██║    ██╔══██╗██║   ██║██║   ██║██║╚██╗██║██║  ██║\n");
        newRound.add("██║ ╚████║███████╗╚███╔███╔╝    ██║  ██║╚██████╔╝╚██████╔╝██║ ╚████║██████╔╝\n");
        newRound.add("╚═╝  ╚═══╝╚══════╝ ╚══╝╚══╝     ╚═╝  ╚═╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝╚═════╝ \n");
        return newRound;
    }

    public List<String> getHighscoresMessage() {
        List<String> newRound = new ArrayList<>();
        newRound.add("██╗  ██╗██╗ ██████╗ ██╗  ██╗███████╗ ██████╗ ██████╗ ██████╗ ███████╗███████╗\n");
        newRound.add("██║  ██║██║██╔════╝ ██║  ██║██╔════╝██╔════╝██╔═══██╗██╔══██╗██╔════╝██╔════╝\n");
        newRound.add("███████║██║██║  ███╗███████║███████╗██║     ██║   ██║██████╔╝█████╗  ███████╗\n");
        newRound.add("██╔══██║██║██║   ██║██╔══██║╚════██║██║     ██║   ██║██╔══██╗██╔══╝  ╚════██║\n");
        newRound.add("██║  ██║██║╚██████╔╝██║  ██║███████║╚██████╗╚██████╔╝██║  ██║███████╗███████║\n");
        newRound.add("╚═╝  ╚═╝╚═╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝\n");
        return newRound;
    }

    public List<String> getGameTitle() {
        List<String> gameName = new ArrayList<>();
        gameName.add("██████╗  ██████╗ ███╗   ███╗██████╗  █████╗ ██████╗ ██████╗ ██╗███████╗██████╗     ██╗██╗");
        gameName.add("██╔══██╗██╔═══██╗████╗ ████║██╔══██╗██╔══██╗██╔══██╗██╔══██╗██║██╔════╝██╔══██╗    ██║██║");
        gameName.add("██████╔╝██║   ██║██╔████╔██║██████╔╝███████║██████╔╝██║  ██║██║█████╗  ██████╔╝    ██║██║");
        gameName.add("██╔══██╗██║   ██║██║╚██╔╝██║██╔══██╗██╔══██║██╔══██╗██║  ██║██║██╔══╝  ██╔══██╗    ██║██║");
        gameName.add("██████╔╝╚██████╔╝██║ ╚═╝ ██║██████╔╝██║  ██║██║  ██║██████╔╝██║███████╗██║  ██║    ██║██║");
        gameName.add("╚═════╝  ╚═════╝ ╚═╝     ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝ ╚═╝╚══════╝╚═╝  ╚═╝    ╚═╝╚═╝");
        return gameName;
    }
}
