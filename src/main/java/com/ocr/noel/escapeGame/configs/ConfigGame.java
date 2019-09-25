package com.ocr.noel.escapeGame.configs;

import com.ocr.noel.escapeGame.enums.ConfigGameEnum;
import com.ocr.noel.escapeGame.utils.ConfigUtil;

public class ConfigGame {
    private Integer numbersLength;
    private Integer nbTestMax;
    boolean devMode = false;
    private static ConfigGame configGame = null;

    /**
     * This constructor is not accessible excepted in this class
     */
    private ConfigGame() {
    }

    /**
     * get config of the game<br/>
     * config file will be read only once
     * @return the config game
     */
    public static ConfigGame getConfigGame() {
        if (configGame == null) {
            configGame = new ConfigGame();
            ConfigUtil.setUpConfigGameFromPropertiesFile(configGame);
        }
        return configGame;
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
