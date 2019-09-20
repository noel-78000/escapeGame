package com.ocr.noel.escapeGame.configs;

import com.ocr.noel.escapeGame.enums.ConfigGameEnum;
import com.ocr.noel.escapeGame.utils.ConfigUtil;

public class ConfigGame {
    private Integer numbersLength;
    private Integer nberTestMax;
    boolean devMode = false;
    private static ConfigGame configGame = null;

    /**
     * This constructor is not accessible excepted in this class
     */
    private ConfigGame() {
    }

    public static ConfigGame getConfigGame() {
        if (configGame == null) {
            configGame = new ConfigGame();
            ConfigUtil.setUpConfigGameFromXMLFile(ConfigGameEnum.CONFIG_FILE_NAME.getName(), configGame);
        }
        return configGame;
    }

    public Integer getNumbersLength() {
        return numbersLength;
    }

    public void setNumbersLength(Integer numbersLength) {
        this.numbersLength = numbersLength;
    }

    public Integer getNberTestMax() {
        return nberTestMax;
    }

    public void setNberTestMax(Integer nberTestMax) {
        this.nberTestMax = nberTestMax;
    }

    public boolean isDevMode() {
        return devMode;
    }

    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }
}
