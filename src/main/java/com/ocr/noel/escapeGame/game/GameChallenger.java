package com.ocr.noel.escapeGame.game;

import com.ocr.noel.escapeGame.configs.ConfigGame;
import com.ocr.noel.escapeGame.enums.GameChoiceEnum;
import com.ocr.noel.escapeGame.utils.ConfigUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameChallenger extends GameMode {
    private final static Logger log = LogManager.getLogger(GameChallenger.class);

    public GameChallenger() {
    }

    @Override
    public void startGame() {
        log.info("starting game");
        setNumberOfTest(0);
        System.out.println("mode: " + GameChoiceEnum.CHALLENGER.getDescription() + " choisi");
        if (getSecretNumberArray() == null) {
            setSecretNumberArray(ConfigUtil.generateRandomIntegerArray(ConfigGame.getInstance().getNumbersLength()));
        }
        if (ConfigGame.getInstance().isDevMode()) {
            System.out.println(String.format("Mode dev -> le nombre secret est : %d", ConfigUtil.getIntFromIntArray(getSecretNumberArray())));
        }
        while (getNumberOfTest() < ConfigGame.getInstance().getNbTestMax()) {
            if (isNewEntryOK()) {
                System.out.println("Vous avez gagné!");
                setSecretNumberArray(null);
                log.info("end of this game");
                return;
            }
        }
        System.out.println(String.format("Le nombre secret était: %d", ConfigUtil.getIntFromIntArray(getSecretNumberArray())));
        System.out.println("Vous avez perdu!");
        setSecretNumberArray(null);
        log.info("end of this game");
    }
}
