package com.ocr.noel.escapeGame.game;

import com.ocr.noel.escapeGame.configs.ConfigGame;
import com.ocr.noel.escapeGame.enums.GameChoiceEnum;
import com.ocr.noel.escapeGame.utils.ConfigUtil;

import java.util.Scanner;

public class GameDuel extends GameMode {

    public GameDuel(Scanner scannerIn, ConfigGame configGame) {
        super(scannerIn, configGame);
    }

    @Override
    public void startGame() {
        setNumberOfTest(0);
        AIMemory aiMemory = new AIMemory(getConfigGame().getNumbersLength());
        System.out.println("mode: " + GameChoiceEnum.DUEL.getDescription() + " choisi");
        setSecretNumberArray(ConfigUtil.generateRandomIntegerArray(getConfigGame().getNumbersLength()));
        if (getConfigGame().isDevMode()) {
            System.out.println("Mode dev -> le nombre secret est : " + ConfigUtil.getIntFromIntArray(getSecretNumberArray()));
        }
        boolean gameOver = false;
        String lastResultForAI = null;
        while (!gameOver) {
            if (isNewEntryOK()) {
                System.out.println("Vous avez gagné!");
                gameOver = true;
            }
            if (!gameOver) {
                int[] numberAI = aiMemory.getNewNumber(lastResultForAI);
                String resNumberAI = Integer.toString(ConfigUtil.getIntFromIntArray(numberAI));
                lastResultForAI = getStringCompare(resNumberAI, getSecretNumberArray());
                System.out.println("L\'ordinateur a donné: " + resNumberAI + ", le resultat est: " + lastResultForAI);
                if (lastResultForAI.replace("=", "").length() == 0) {
                    System.out.println("L\'ordinateur a gagné!");
                    gameOver=true;
                }
            }
        }
    }
}
