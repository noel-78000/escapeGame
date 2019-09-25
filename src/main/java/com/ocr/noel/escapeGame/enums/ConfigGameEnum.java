package com.ocr.noel.escapeGame.enums;

public enum ConfigGameEnum {
    NBRE_CHIFFRES ("nbreChiffres"),
    NBRE_ESSAIS_MAX("nbreEssaisMax"),
    MODE_DEVELOPPEUR ("modeDeveloppeur"),
    PROPERTIES_FILE_NAME("config");

    private final String name;

    ConfigGameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
