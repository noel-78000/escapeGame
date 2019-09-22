package com.ocr.noel.escapeGame;

import com.ocr.noel.escapeGame.game.Game;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
