package com.ocr.noel.escapeGame.game;

import com.ocr.noel.escapeGame.utils.ConfigUtil;

public abstract class GameMode {
    private int numberOfTest = 0;
    private int[] secretNumberArray = null;

    /**
     * Constructor of game
     */
    public GameMode() {
    }

    /**
     * The method to start a mode of game
     */
    public abstract void startGame();

    protected boolean askIfReplayGame() {
        System.out.println("Rejouer la partie? (O/N)");
        while (ConfigUtil.getScannerIn().hasNext()) {
            String line = ConfigUtil.getScannerIn().nextLine().trim().toUpperCase();
            switch (line) {
                case "O":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println(String.format("Votre entrée clavier est erroné, vous avec entré: %s, veuillez réessayer.", line));
            }
        }
        return false;
    }

    /**
     * This method ask a new number and test it if match
     *
     * @return true if the number match, otherwise false
     */
    protected boolean isNewEntryOK() {
        System.out.print(String.format("Entrer le nombre à %d chiffres: ", secretNumberArray.length));
        while (ConfigUtil.getScannerIn().hasNext()) {
            String intString = ConfigUtil.getScannerIn().nextLine().trim();
            Integer consoleEntry = ConfigUtil.getIntegerFromString(intString);
            if (consoleEntry != null && consoleEntry >= 0) {
                if (intString.length() != getSecretNumberArray().length) {
                    System.out.println(String.format("Votre nombre est de longueur incorrecte, il devrait être composer de %d chiffres, veuillez recommencer.", getSecretNumberArray().length));
                    continue;
                }
                String compareResult = getStringCompare(intString, getSecretNumberArray());
                this.numberOfTest++;
                System.out.println(String.format("Le resultat est : %s", compareResult));
                if (compareResult.replace("=", "").length() == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                System.out.println(String.format("Votre saisie est érroné, vous avez taper: %s", intString));
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
        String compareResult = "";
        for (int i = 0; i < secretNumberArray.length; i++) {
            int val = Integer.parseInt(intString.substring(i, i + 1));
            if (val == secretNumberArray[i]) {
                compareResult += "=";
                continue;
            } else if (val > secretNumberArray[i]) {
                compareResult += "-";
                continue;
            } else {
                compareResult += "+";
            }
        }
        return compareResult;
    }

    public int getNumberOfTest() {
        return numberOfTest;
    }

    public void setNumberOfTest(int numberOfTest) {
        this.numberOfTest = numberOfTest;
    }

    public int[] getSecretNumberArray() {
        return secretNumberArray;
    }

    public void setSecretNumberArray(int[] secretNumberArray) {
        this.secretNumberArray = secretNumberArray;
    }
}
