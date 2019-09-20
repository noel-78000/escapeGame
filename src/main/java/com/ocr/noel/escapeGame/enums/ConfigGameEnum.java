package com.ocr.noel.escapeGame.enums;

public enum ConfigGameEnum {
    NBRE_CHIFFRES ("nbreChiffres"),
    NBRE_ESSAIS ("nbreEssais"),
    MODE_DEVELOPPEUR ("modeDeveloppeur"),
    CONFIG_FILE_NAME ("configGame.xml");

    private final String name;

    ConfigGameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
