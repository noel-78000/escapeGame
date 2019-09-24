package com.ocr.noel.escapeGame.game;

import com.ocr.noel.escapeGame.configs.ConfigGame;
import com.ocr.noel.escapeGame.enums.GameChoiceEnum;
import com.ocr.noel.escapeGame.utils.ConfigUtil;

import java.util.Scanner;

public class GameChallenger extends GameMode {

    public GameChallenger(Scanner scannerIn, ConfigGame configGame) {
        super(scannerIn, configGame);
    }

    @Override
    public void startGame() {
        setNumberOfTest(0);
        System.out.println("mode: " + GameChoiceEnum.CHALLENGER.getDescription() + " choisi");
        if (getSecretNumberArray() == null) {
            setSecretNumberArray(ConfigUtil.generateRandomIntegerArray(getConfigGame().getNumbersLength()));
        }
        if (getConfigGame().isDevMode()) {
            System.out.println("Mode dev -> le nombre secret est : " + ConfigUtil.getIntFromIntArray(getSecretNumberArray()));
        }
        while (getNumberOfTest() < getConfigGame().getNbTestMax()) {
            if (isNewEntryOK()) {
                System.out.println("Vous avez gagné!");
                setSecretNumberArray(null);
                return;
            }
        }
        System.out.println("Le nombre secret était: " + ConfigUtil.getIntFromIntArray(getSecretNumberArray()));
        System.out.println("Vous avez perdu!");
        setSecretNumberArray(null);
    }
}
