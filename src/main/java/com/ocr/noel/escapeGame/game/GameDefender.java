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

    /**
     * this method get a correct result with the good length as "=+-="
     *
     * @return the result comparison from the keyboard
     */
    private String getResultComparison() {
        while (ConfigUtil.getScannerIn().hasNext()) {
            String line = ConfigUtil.getScannerIn().nextLine().trim();
            if (line.length() == ConfigGame.getInstance().getNumbersLength() &&
                    line.replace("=", "").replace("+", "").replace("-", "").length() == 0) {
                return line;
            } else {
                System.out.println(String.format("Il y a une erreur dans la saisie. Veuillez saisir %d de = ou + ou -", ConfigGame.getInstance().getNumbersLength()));
            }
        }
        return null;
    }

    /**
     * This method ask a new available secret number of the gamer
     *
     * @return the secret number as an array from keyboard or null if pb occurred
     */
    private int[] getConsoleInputSecretNumberArrayFromGamer() {
        System.out.print(String.format("Entrer le nombre secret de %d chiffres: ", ConfigGame.getInstance().getNumbersLength()));
        while (ConfigUtil.getScannerIn().hasNext()) {
            String secretNumber = ConfigUtil.getScannerIn().nextLine().trim();
            if (secretNumber.length() != ConfigGame.getInstance().getNumbersLength() || ConfigUtil.getIntegerFromString(secretNumber) == null) {
                System.out.println(String.format("Erreur, le nombre doit être de %d chiffres, veuillez réessayer.", ConfigGame.getInstance().getNumbersLength()));
                continue;
            }
            return ConfigUtil.getIntArrayFromStringNumber(secretNumber);
        }
        return null;
    }
}
