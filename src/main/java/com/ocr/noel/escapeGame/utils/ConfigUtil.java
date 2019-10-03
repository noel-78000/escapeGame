package com.ocr.noel.escapeGame.utils;

import com.ocr.noel.escapeGame.configs.ConfigGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ConfigUtil {
    private static final Logger log = LogManager.getLogger(ConfigUtil.class);

    private static Scanner scannerIn = new Scanner(System.in);

    /**
     * Convert a String to Integer if is possible, otherwise return null
     *
     * @param intString String to convert in Integer
     * @return the Integer into the String or null if it is not an Integer
     */
    public static Integer getIntegerFromString(String intString) {
        Integer repInt = null;
        try {
            repInt = Integer.valueOf(intString);
        } catch (NumberFormatException e) {
            log.error("Error to convert a String to an integer");
        }
        return repInt;
    }

    /**
     * get the scanner to read from input console
     *
     * @return the scanner to read from input console
     */
    public static Scanner getScannerIn() {
        return scannerIn;
    }

    /**
     * This method create a random secret number array
     *
     * @param length the length of the secret number to discover
     * @return the secret number as a int[]
     */
    public static int[] generateRandomIntegerArray(int length) {
        log.debug(String.format("Starting method generateRandomIntegerArray, with length = %d", length));
        int[] secretNumber = new int[length];
        for (int i = 0; i < secretNumber.length; i++) {
            String partOfRandomNumber = String.valueOf(Math.random());
            String aNumber = partOfRandomNumber.substring(partOfRandomNumber.length() - 1);
            secretNumber[i] = Integer.parseInt(aNumber);
        }
        return secretNumber;
    }

    /**
     * This method get an int displayable on X characters from this representative int array
     *
     * @param secretNumberTab the int array to transform
     * @return an displayable int from the parameter or null if error occurred
     */
    public static String getDisplayableIntFromIntArray(int[] secretNumberTab) {
        if (secretNumberTab == null || secretNumberTab.length == 0) {
            return null;
        }
        int res = 0;
        for (int i = 0; i < secretNumberTab.length; i++) {
            res += secretNumberTab[i] * Math.pow(10, secretNumberTab.length - 1 - i);
        }
        StringBuffer sb = new StringBuffer(String.valueOf(res));
        //try to get a String on ConfigGame.getInstance().getNumbersLength() character
        while (ConfigGame.getInstance().getNumbersLength() > sb.length()) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    /**
     * this method return an int array from a String number
     *
     * @param number the number to convert into an array of int
     * @return an int array from a String number
     */
    public static int[] getIntArrayFromStringNumber(String number) {
        int[] intArray = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            intArray[i] = Integer.parseInt(number.substring(i, i + 1));
        }
        return intArray;
    }
}
