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
        AIMemory aiMemory = new AIMemory(getConfigGame().getNumbersLength());
        String resultComparison = null;
        boolean computerWin = false;
        do {
            int[] newProposalNumber = aiMemory.getNewNumber(resultComparison);
            System.out.print("L\'ordinateur propose: " + ConfigUtil.getIntFromIntArray(newProposalNumber) + ", entrer le résultat: ");
            setNumberOfTest(getNumberOfTest() + 1);
            resultComparison = getResultComparison();
            if (resultComparison.replace("=","").length() == 0){
                computerWin = true;
            }
        } while (!computerWin && getNumberOfTest() < getConfigGame().getNbTestMax());
        if (computerWin) {
            System.out.println("L\'ordinateur a gagné!");
        } else {
            System.out.println("L\'ordinateur a perdu!");
        }
    }

    /**
     * this method get a correct result with the good length as "=+-="
     *
     * @return the result comparison from the keyboard
     */
    private String getResultComparison() {
        while (getScannerIn().hasNext()) {
            String line = getScannerIn().nextLine().trim();
            if (line.length() == getConfigGame().getNumbersLength() &&
                    line.replace("=","").replace("+","").replace("-","").length() == 0) {
                return line;
            } else {
                System.out.println("Il y a une erreur dans la saisie. Veuillez saisir " + getConfigGame().getNumbersLength() + " de = ou + ou -");
            }
        }
        return null;
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
