package com.ocr.noel.escapeGame.configs;

import java.util.ResourceBundle;

public class ConfigGame {
    private Integer numbersLength, nbTestMax;
    private boolean devMode;
    private static ConfigGame instance = null;

    /**
     * This constructor is not accessible excepted in this class because this is a singleton<br/>
     * config file will be read only once
     */
    private ConfigGame() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        numbersLength = Integer.parseInt(resourceBundle.getString("numbersLength"));
        nbTestMax = Integer.parseInt(resourceBundle.getString("nbTestMax"));
        devMode = Boolean.parseBoolean(resourceBundle.getString("devMode"));
    }

    /**
     * get config of the game<br/>
     * the return instance will be construct only once because this is a singleton
     *
     * @return the config game
     */
    public static ConfigGame getInstance() {
        if (instance == null) {
            instance = new ConfigGame();
        }
        return instance;
    }

    public Integer getNumbersLength() {
        return numbersLength;
    }

    public void setNumbersLength(Integer numbersLength) {
        this.numbersLength = numbersLength;
    }

    public Integer getNbTestMax() {
        return nbTestMax;
    }

    public void setNbTestMax(Integer nbTestMax) {
        this.nbTestMax = nbTestMax;
    }

    public boolean isDevMode() {
        return devMode;
    }

    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }
}
