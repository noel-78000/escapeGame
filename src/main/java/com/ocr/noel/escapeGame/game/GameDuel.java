package com.ocr.noel.escapeGame.game;

import com.ocr.noel.escapeGame.configs.ConfigGame;
import com.ocr.noel.escapeGame.enums.GameChoiceEnum;
import com.ocr.noel.escapeGame.utils.ConfigUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of duel mode,
 * the computer and the gamer choice a secret number.
 * So, the first who found the number of the other won
 */
public class GameDuel extends GameMode {
    private final static Logger log = LogManager.getLogger(GameDuel.class);

    public GameDuel() {
    }

    @Override
    public void startGame() {
        log.info("starting game");
        System.out.println(String.format("mode: %s choisi", GameChoiceEnum.DUEL.getDescription()));
        setNumberOfTest(0);
        AIMemory aiMemory = new AIMemory(ConfigGame.getInstance().getNumbersLength());
        setSecretNumberArrayFromComputer(ConfigUtil.generateRandomIntegerArray(ConfigGame.getInstance().getNumbersLength()));
        if (ConfigGame.getInstance().isDevMode()) {
            System.out.println(String.format("Mode dev -> le nombre secret de l'ordinateur est : %s", ConfigUtil.getDisplayableIntFromIntArray(getSecretNumberArrayFromComputer())));
        }
        setSecretNumberArrayFromGamer(getConsoleInputSecretNumberArrayFromGamer());
        boolean gameOver = false;
        String lastResultForAI = null;
        while (!gameOver) {
            if (isNewEntryFromGamerOK()) {
                System.out.println(String.format("%sVous avez gagné!", System.lineSeparator()));
                gameOver = true;
            }
            if (!gameOver) {
                int[] numberAI = aiMemory.getNewNumber(lastResultForAI);
                String resNumberAI = ConfigUtil.getDisplayableIntFromIntArray(numberAI);
                System.out.println(String.format("L'ordinateur a donné: %s", resNumberAI));
                System.out.print("Le résultat est: ");
                lastResultForAI = getResultComparisonFromKeyboardEntries();
                log.debug("The AI give {}, the result is {}", resNumberAI, lastResultForAI);
                if (lastResultForAI.replace("=", "").length() == 0) {
                    System.out.println(String.format("%sL'ordinateur a gagné!", System.lineSeparator()));
                    gameOver = true;
                }
            }
        }
        log.info("end of this game");
    }
}
