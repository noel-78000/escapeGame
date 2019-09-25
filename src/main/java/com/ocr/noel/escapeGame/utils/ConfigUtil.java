package com.ocr.noel.escapeGame.utils;

import com.ocr.noel.escapeGame.configs.ConfigGame;
import com.ocr.noel.escapeGame.enums.ConfigGameEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ConfigUtil {
    private static final Logger log = LogManager.getLogger(ConfigUtil.class);

    private static Scanner scannerIn = new Scanner(System.in);

    /**
     * This method get configuration from config.properties
     *
     * @param newConfigGame the new config of game to update
     * @return the newConfigGame configured
     */
    public static ConfigGame setUpConfigGameFromPropertiesFile(ConfigGame newConfigGame) {
        ResourceBundle config = ResourceBundle.getBundle(ConfigGameEnum.PROPERTIES_FILE_NAME.getName());
        try {
            ResourceBundle externalConfigProps = new PropertyResourceBundle(Files.newInputStream(Paths.get(ConfigGameEnum.PROPERTIES_FILE_NAME.getName() + ".properties")));
            config = externalConfigProps;
            log.info("External config.properties file found, it will be used");
        } catch (IOException e) {
            log.debug("No external existing " + ConfigGameEnum.PROPERTIES_FILE_NAME.getName() + ".properties file, the default config.properties will be used");
        }
        int nberTestMax = Integer.parseInt(config.getString(ConfigGameEnum.NBRE_ESSAIS_MAX.getName()));
        newConfigGame.setNbTestMax(nberTestMax);
        boolean devMode = Boolean.valueOf(config.getString(ConfigGameEnum.MODE_DEVELOPPEUR.getName()));
        newConfigGame.setDevMode(devMode);
        int numberLength = Integer.parseInt(config.getString(ConfigGameEnum.NBRE_CHIFFRES.getName()));
        newConfigGame.setNumbersLength(numberLength);
        return newConfigGame;
    }

    /**
     * Convert a String to Integer if is possible otherwise return null
     *
     * @param intString String to convert in Integer
     * @param logErrorMessage message to log if an error occurred
     * @return the Integer into the String or null if it is not an Integer
     */
    public static Integer getIntegerFromString(String intString, String logErrorMessage) {
        Integer repInt = null;
        try {
            repInt = Integer.valueOf(intString);
        } catch (NumberFormatException e) {
            log.error(logErrorMessage);
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
     * This method create a secret number array
     *
     * @param length the length of the number to discover
     * @return the secret number as a int[]
     */
    public static int[] generateRandomIntegerArray(int length) {
        int[] secretNumber = new int[length];
        for (int i = 0; i < secretNumber.length; i++) {
            String partOfRandomNumber = String.valueOf(Math.random());
            String aNumber = partOfRandomNumber.substring(partOfRandomNumber.length() - 1);
            secretNumber[i] = Integer.parseInt(aNumber);
        }
        return secretNumber;
    }

    /**
     * This method get an int from this representative int array
     *
     * @param secretNumberTab the int array to transform
     * @return an int from the parameter or -1 if error occurred
     */
    public static int getIntFromIntArray(int[] secretNumberTab) {
        if (secretNumberTab == null || secretNumberTab.length == 0) {
            return -1;
        }
        int res = 0;
        for (int i = 0; i < secretNumberTab.length; i++) {
            res += secretNumberTab[i] * Math.pow(10, secretNumberTab.length - 1 - i);
        }
        return res;
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
