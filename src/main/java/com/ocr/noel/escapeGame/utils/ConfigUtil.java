package com.ocr.noel.escapeGame.utils;

import com.ocr.noel.escapeGame.configs.ConfigGame;
import com.ocr.noel.escapeGame.enums.ConfigGameEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.Scanner;

public class ConfigUtil {
    private static final Logger log = LogManager.getLogger(ConfigUtil.class);

    private static Scanner scannerIn = new Scanner(System.in);

    /**
     * this method get configuration from the XML file configGame.xml
     * @param xmlFileName the name of the XML config file
     * @param newConfigGame the new config of game to update
     * @return the newConfigGame configured
     */
    public static ConfigGame setUpConfigGameFromXMLFile(String xmlFileName, ConfigGame newConfigGame) {
        try {
            SAXBuilder jdomBuilder = new SAXBuilder();
            Document jdomDocument = jdomBuilder.build(xmlFileName);
            Element root = jdomDocument.getRootElement();

            String numbersLengthString = root.getChild(ConfigGameEnum.NBRE_CHIFFRES.getName()).getText();
            Integer numbersLength;
            numbersLength = getIntegerFromString(numbersLengthString, "Error of length of number, in file: " + xmlFileName + "default length will be 4");
            if (numbersLength == null) {
                numbersLength = 4; // default length is 4
            }
            newConfigGame.setNumbersLength(numbersLength);

            String nberTestMaxString = root.getChild(ConfigGameEnum.NBRE_ESSAIS_MAX.getName()).getText();
            Integer nberTestMax = getIntegerFromString(nberTestMaxString, "Error of number of test max, update your file configuration please: " + xmlFileName);
            if (nberTestMax == null) {
                return null;
            }
            newConfigGame.setNbTestMax(nberTestMax);

            String devModeString = root.getChild(ConfigGameEnum.MODE_DEVELOPPEUR.getName()).getText();
            boolean devMode = false;
            if (devModeString != null && devModeString.compareToIgnoreCase("O") == 0) {
                devMode = true;
            }
            newConfigGame.setDevMode(devMode);
        } catch (JDOMException e) {
            log.error("Error in file: " + xmlFileName, e);
        } catch (IOException e) {
            log.error("Error in access to configuration configuration; " + xmlFileName, e);
        }
        return newConfigGame;
    }

    /**
     * Convert a String to Integer if is possible otherwise return null
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
     * @return the scanner to read from input console
     */
    public static Scanner getScannerIn() {
        return scannerIn;
    }

    /**
     * This method create a secret number array
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
}
