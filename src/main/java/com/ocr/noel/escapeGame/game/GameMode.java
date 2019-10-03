package com.ocr.noel.escapeGame.game;

import com.ocr.noel.escapeGame.configs.ConfigGame;
import com.ocr.noel.escapeGame.utils.ConfigUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Common abstract class for the different game mode
 */
public abstract class GameMode {
    private final static Logger log = LogManager.getLogger(GameMode.class);

    //Number of test already done by the computer and the gamer
    private int numberOfTest = 0;

    //The secret number chosen by the gamer
    private int[] secretNumberArrayFromGamer = null;

    //The secret number given by the computer
    private int[] secretNumberArrayFromComputer = null;

    /**
     * Constructor of game
     */
    public GameMode() {
    }

    /**
     * The method to start a mode of game
     */
    public abstract void startGame();

    /**
     * This method ask to the gamer if he want to replay the same game or not
     *
     * @return true if the game will be replay false otherwise
     */
    protected boolean askIfReplayGame() {
        System.out.println("Rejouer la partie? (O/N)");
        while (ConfigUtil.getScannerIn().hasNext()) {
            String line = ConfigUtil.getScannerIn().nextLine().trim().toUpperCase();
            switch (line) {
                case "O":
                    System.out.println("------------------------------------");
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println(String.format("Votre entrée clavier est erronée, vous avez entré: %s, veuillez réessayer.", line));
            }
        }
        return false;
    }

    /**
     * This method ask a new number and test it if match with the secret number
     *
     * @return true if the number match, otherwise false
     */
    protected boolean isNewEntryFromGamerOK() {
        this.numberOfTest++;
        System.out.println(String.format("%sEssai numéro %d,", System.lineSeparator(), getNumberOfTest()));
        System.out.print(String.format("Entrer le nombre à %d chiffres: ", getSecretNumberArrayFromComputer().length));
        while (ConfigUtil.getScannerIn().hasNext()) {
            String intString = ConfigUtil.getScannerIn().nextLine().trim();
            Integer consoleEntry = ConfigUtil.getIntegerFromString(intString);
            if (consoleEntry != null && consoleEntry >= 0) {
                if (intString.length() != getSecretNumberArrayFromComputer().length) {
                    log.debug("The entry of the gamer is: {}, this number length is wrong", intString);
                    System.out.println(String.format("Votre nombre est de longueur incorrecte, il devrait être composer de %d chiffres, veuillez recommencer.", getSecretNumberArrayFromComputer().length));
                    continue;
                }
                String compareResult = getStringCompare(intString, getSecretNumberArrayFromComputer());
                System.out.println(String.format("le resultat est : %s", compareResult));
                log.debug("The player\'s keyboard input is {}, the result is {}", intString, compareResult);
                if (compareResult.replace("=", "").length() == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                System.out.println(String.format("Votre saisie est érronée, vous avez tapé: %s, veuillez recommencer.", intString));
                log.debug("The entry of the gamer is: {}, this is not a number!", intString);
            }
        }
        return false;
    }

    /**
     * compare the String number to the secret number and return the String result formatted
     *
     * @param intString         number to compare with the secret number
     * @param secretNumberArray the secret number
     * @return the result of the the comparison between the two arguments
     */
    protected String getStringCompare(String intString, int[] secretNumberArray) {
        StringBuffer compareResult = new StringBuffer();
        for (int i = 0; i < secretNumberArray.length; i++) {
            int val = Integer.parseInt(intString.substring(i, i + 1));
            if (val == secretNumberArray[i]) {
                compareResult.append("=");
                continue;
            } else if (val > secretNumberArray[i]) {
                compareResult.append("-");
                continue;
            } else {
                compareResult.append("+");
            }
        }
        return compareResult.toString();
    }

    /**
     * This method ask a new available secret number of the gamer
     *
     * @return the secret number as an array from keyboard or null if pb occurred
     */
    protected int[] getConsoleInputSecretNumberArrayFromGamer() {
        System.out.print(String.format("Entrer le nombre secret de %d chiffres: ", ConfigGame.getInstance().getNumbersLength()));
        while (ConfigUtil.getScannerIn().hasNext()) {
            String secretNumber = ConfigUtil.getScannerIn().nextLine().trim();
            if (secretNumber.length() != ConfigGame.getInstance().getNumbersLength() || ConfigUtil.getIntegerFromString(secretNumber) == null) {
                System.out.println(String.format("Erreur, le nombre doit être de %d chiffres, veuillez réessayer.", ConfigGame.getInstance().getNumbersLength()));
                log.debug("The new secret number is wrong: {}, a number of length {} is expected", secretNumber, ConfigGame.getInstance().getNumbersLength());
                continue;
            }
            return ConfigUtil.getIntArrayFromStringNumber(secretNumber);
        }
        return null;
    }

    /**
     * this method get result from the input console and
     * if the result is rightly written with the good String symbol and length as "=+-="
     *
     * @return the result comparison from the keyboard
     */
    protected String getResultComparisonFromKeyboardEntries() {
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

    public int getNumberOfTest() {
        return numberOfTest;
    }

    public void setNumberOfTest(int numberOfTest) {
        this.numberOfTest = numberOfTest;
    }

    public int[] getSecretNumberArrayFromGamer() {
        return secretNumberArrayFromGamer;
    }

    public void setSecretNumberArrayFromGamer(int[] secretNumberArray) {
        this.secretNumberArrayFromGamer = secretNumberArray;
    }

    public int[] getSecretNumberArrayFromComputer() {
        return secretNumberArrayFromComputer;
    }

    public void setSecretNumberArrayFromComputer(int[] secretNumberArrayFromComputer) {
        this.secretNumberArrayFromComputer = secretNumberArrayFromComputer;
    }
}
