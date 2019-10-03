package com.ocr.noel.escapeGame.game;

import com.ocr.noel.escapeGame.configs.ConfigGame;
import com.ocr.noel.escapeGame.enums.GameChoiceEnum;
import com.ocr.noel.escapeGame.utils.ConfigUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of defender mode,
 * the gamer choice the secret number and the computer have to find it
 */
public class GameDefender extends GameMode {
    private final static Logger log = LogManager.getLogger(GameDefender.class);

    public GameDefender() {
    }

    @Override
    public void startGame() {
        log.info("starting game");
        setNumberOfTest(0);
        System.out.println(String.format("mode: %s choisi", GameChoiceEnum.DEFENDER.getDescription()));
        setSecretNumberArrayFromGamer(getConsoleInputSecretNumberArrayFromGamer());
        AIMemory aiMemory = new AIMemory(ConfigGame.getInstance().getNumbersLength());
        String resultComparison = null;
        boolean computerWin = false;
        do {
            int[] newProposalNumber = aiMemory.getNewNumber(resultComparison);
            setNumberOfTest(getNumberOfTest() + 1);
            System.out.println(String.format("Coups numéro %d, l\'ordinateur propose: %s", getNumberOfTest(), ConfigUtil.getDisplayableIntFromIntArray(newProposalNumber)));
            System.out.print("Entrer le résultat: ");
            resultComparison = getResultComparison();
            if (resultComparison.replace("=", "").length() == 0) {
                computerWin = true;
            }
        } while (!computerWin && getNumberOfTest() < ConfigGame.getInstance().getNbTestMax());
        if (computerWin) {
            System.out.println(String.format("L\'ordinateur a gagné en %d coups!", getNumberOfTest()));
        } else {
            System.out.println("L\'ordinateur a perdu!");
        }
        log.info("end of this game");
        setSecretNumberArrayFromGamer(null);
    }
}
