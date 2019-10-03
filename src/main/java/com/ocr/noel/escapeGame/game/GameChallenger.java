package com.ocr.noel.escapeGame.game;

import com.ocr.noel.escapeGame.configs.ConfigGame;
import com.ocr.noel.escapeGame.enums.GameChoiceEnum;
import com.ocr.noel.escapeGame.utils.ConfigUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of challenger mode,
 * the computer chose a number and the gamer have to find it
 */
public class GameChallenger extends GameMode {
    private final static Logger log = LogManager.getLogger(GameChallenger.class);

    public GameChallenger() {
    }

    @Override
    public void startGame() {
        log.info("starting game");
        setNumberOfTest(0);
        System.out.println(String.format("mode: %s choisi", GameChoiceEnum.CHALLENGER.getDescription()));
        if (getSecretNumberArrayFromComputer() == null) {
            setSecretNumberArrayFromComputer(ConfigUtil.generateRandomIntegerArray(ConfigGame.getInstance().getNumbersLength()));
        }
        if (ConfigGame.getInstance().isDevMode()) {
            System.out.println(String.format("Mode dev -> le nombre secret de l\'ordinateur est : %s", ConfigUtil.getDisplayableIntFromIntArray(getSecretNumberArrayFromComputer())));
        }
        while (getNumberOfTest() < ConfigGame.getInstance().getNbTestMax()) {
            if (isNewEntryFromGamerOK()) {
                System.out.println(String.format("%sVous avez gagné en %d coups!", System.lineSeparator(), getNumberOfTest()));
                setSecretNumberArrayFromComputer(null);
                log.info("end of this game");
                return;
            }
        }
        System.out.println(String.format("%sLe nombre secret était: %s", System.lineSeparator(), ConfigUtil.getDisplayableIntFromIntArray(getSecretNumberArrayFromComputer())));
        System.out.println("Vous avez perdu!");
        setSecretNumberArrayFromComputer(null);
        log.info("end of this game");
    }
}
