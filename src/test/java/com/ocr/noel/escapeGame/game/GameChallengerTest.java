package com.ocr.noel.escapeGame.game;

import com.ocr.noel.escapeGame.configs.ConfigGame;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameChallengerTest {

    @Test
    void Given_goodNumber_When_startGame_Then_gameWon() throws IOException {
        String input = "1234" + System.lineSeparator();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        ConfigGame configGame = ConfigGame.getConfigGame();
        configGame.setNumbersLength(4);
        configGame.setDevMode(false);
        configGame.setNbTestMax(3);
        GameChallenger gameChallenger = new GameChallenger(scanner, configGame);
        gameChallenger.setSecretNumberArray(new int[]{1, 2, 3, 4});
        ByteArrayOutputStream bb = new ByteArrayOutputStream();
        java.io.BufferedOutputStream outputStream = new BufferedOutputStream(bb);
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        gameChallenger.startGame();
        printStream.flush();
        String[] lines = bb.toString().split(System.lineSeparator());
        assertEquals("Vous avez gagné!", lines[lines.length - 1]);
    }

    @Test
    void Given_wrongNumber_When_startGame_Then_gameLost() {
        String input = "4523" + System.lineSeparator();
        input += "7236" + System.lineSeparator();
        input += "6152" + System.lineSeparator();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        ConfigGame configGame = ConfigGame.getConfigGame();
        configGame.setNumbersLength(4);
        configGame.setDevMode(false);
        configGame.setNbTestMax(3);
        GameChallenger gameChallenger = new GameChallenger(scanner, configGame);
        gameChallenger.setSecretNumberArray(new int[]{1, 2, 3, 4});
        ByteArrayOutputStream bb = new ByteArrayOutputStream();
        java.io.BufferedOutputStream outputStream = new BufferedOutputStream(bb);
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        gameChallenger.startGame();
        printStream.flush();
        String[] lines = bb.toString().split(System.lineSeparator());
        assertNotEquals("Vous avez gagné!", lines[lines.length - 1]);
        assertEquals("Vous avez perdu!", lines[lines.length - 1]);
    }

}