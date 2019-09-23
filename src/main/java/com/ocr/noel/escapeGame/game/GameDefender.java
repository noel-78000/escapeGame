package com.ocr.noel.escapeGame.game;

import com.ocr.noel.escapeGame.configs.ConfigGame;
import com.ocr.noel.escapeGame.enums.GameChoiceEnum;
import com.ocr.noel.escapeGame.utils.ConfigUtil;

import java.util.Scanner;

public class GameDefender extends GameMode {

    public GameDefender(Scanner scannerIn, ConfigGame configGame) {
        super(scannerIn, configGame);
    }

    @Override
    public void startGame() {
        setNumberOfTest(0);
        System.out.println("mode: " + GameChoiceEnum.DEFENSEUR.getDescription() + " choisi");
        setSecretNumberArray(getSecretNumberArrayFromGamer());
    }

    /**
     * This method ask a new available secret number of the gamer
     *
     * @return the secret number as an array from keyboard or null if pb occurred
     */
    private int[] getSecretNumberArrayFromGamer() {
        System.out.print("Entrer le nombre secret: ");
        while (getScannerIn().hasNext()) {
            String secretNumber = getScannerIn().nextLine().trim();
            if (secretNumber.length() != getConfigGame().getNumbersLength() || ConfigUtil.getIntegerFromString(secretNumber, "this is not a number") == null) {
                System.out.println("Erreur, le nombre doit être de " + getConfigGame().getNumbersLength() + " chiffres, veuillez réessayer.");
                continue;
            }
            return ConfigUtil.getIntArrayFromStringNumber(secretNumber);
        }
        return null;
    }
}
