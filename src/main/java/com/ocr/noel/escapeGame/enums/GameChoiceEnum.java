package com.ocr.noel.escapeGame.enums;

public enum GameChoiceEnum {
    OTHER ("0", "Autre choix"),
    CHALLENGER ("1", "Challenger"),
    DEFENSEUR ("2", "Defenseur"),
    DUEL ("3", "Duel"),
    GAME_LEAVE ("q" , "Quitter le jeu");

    private final String choice;
    private final String description;

    GameChoiceEnum(String choice, String description) {
        this.choice = choice;
        this.description = description;
    }

    public String getChoice() {
        return choice;
    }

    public String getDescription() {
        return description;
    }
}
