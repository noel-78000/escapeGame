package com.ocr.noel.escapeGame.game;

import com.ocr.noel.escapeGame.utils.ConfigUtil;

/**
 * This class is the artificial intelligence and its memory
 * for the computer try to find the secret number
 */
public class AIMemory {
    private int[] numberMin;
    private int[] numberMax;
    private int[] numberLastProposal;

    /**
     * Constructor of artificial intelligence and memory
     *
     * @param numberLength length of number to find
     */
    public AIMemory(int numberLength) {
        this.numberMin = new int[numberLength];//fill with 0...
        this.numberMax = new int[numberLength];
        for (int i = 0; i < numberLength; i++) {
            this.numberMax[i] = 10;
        }
        this.numberLastProposal = ConfigUtil.generateRandomIntegerArray(numberLength);
    }

    /**
     * In this method AI try to find the good number and return it
     *
     * @param lastResult the last result of its test for example "+==-" or null if any when the game start
     * @return the new array number found
     */
    public int[] getNewNumber(String lastResult) {
        if (lastResult == null || lastResult.length() != numberLastProposal.length) {
            return this.numberLastProposal;
        }
        for (int i = 0; i < lastResult.length(); i++) {
            switch (lastResult.substring(i, i + 1)) {
                case "+":
                    numberMin[i] = numberLastProposal[i];
                    numberLastProposal[i] = (numberMax[i] + numberLastProposal[i]) / 2;
                    break;
                case "-":
                    numberMax[i] = numberLastProposal[i];
                    numberLastProposal[i] = (numberMin[i] + numberLastProposal[i]) / 2;
            }
        }
        return numberLastProposal;
    }
}
