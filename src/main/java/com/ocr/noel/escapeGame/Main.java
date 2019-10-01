package com.ocr.noel.escapeGame;

import com.ocr.noel.escapeGame.game.Game;

public class Main {

    /**
     * The entry point to launch the game
     *
     * @param args the arguments come from the command line to launch this program
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
