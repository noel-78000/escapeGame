package com.ocr.noel.escapeGame.game;

import com.ocr.noel.escapeGame.configs.ConfigGame;
import com.ocr.noel.escapeGame.enums.GameChoiceEnum;
import com.ocr.noel.escapeGame.utils.ConfigUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game {
    private final static Logger log = LogManager.getLogger(Game.class);

    public Game() {
    }

    /**
     * The method to start the game
     */
    public void startGame() {
        log.info("Game start");
        choiceMode();
    }

    /**
     * Method to choice a mode of game or quit the game
     */
    private void choiceMode() {
        printChoiceModeOfGame();
        while (ConfigUtil.getScannerIn().hasNext()) {
            String line = ConfigUtil.getScannerIn().nextLine().trim().toLowerCase();
            switch (line) {
                case "1":
                    GameChallenger gameChallenger = new GameChallenger();
                    gameChallenger.setScannerIn(ConfigUtil.getScannerIn());
                    do {
                        gameChallenger.startGame();
                    } while (gameChallenger.askIfReplayGame());
                    break;

                case "2":
                    GameDefender gameDefender = new GameDefender();
                    gameDefender.setScannerIn(ConfigUtil.getScannerIn());
                    do {
                        gameDefender.startGame();
                    } while (gameDefender.askIfReplayGame());
                    break;

                case "3":
                    GameDuel gameDuel = new GameDuel();
                    gameDuel.setScannerIn(ConfigUtil.getScannerIn());
                    do {
                        gameDuel.startGame();
                    }
                    while (gameDuel.askIfReplayGame());
                    break;

                case "q":
                    stopGame();
                    break;

                default:
                    System.out.println("Votre choix n'est pas correcte, veuillez réessayer");
            }
            printChoiceModeOfGame();
        }
    }

    /**
     * Print to the console the choices of game or quit choice
     */
    private void printChoiceModeOfGame() {
        System.out.println("Choisir un mode de jeu:");
        System.out.println(GameChoiceEnum.CHALLENGER.getChoice() + " - " + GameChoiceEnum.CHALLENGER.getDescription());
        System.out.println(GameChoiceEnum.DEFENDER.getChoice() + " - " + GameChoiceEnum.DEFENDER.getDescription());
        System.out.println(GameChoiceEnum.DUEL.getChoice() + " - " + GameChoiceEnum.DUEL.getDescription());
        System.out.println(GameChoiceEnum.GAME_LEAVE.getChoice() + " - " + GameChoiceEnum.GAME_LEAVE.getDescription());
    }

    private void stopGame() {
        System.out.println("Le jeu est terminé!");
        ConfigUtil.getScannerIn().close();
        log.info("end of the game.");
        System.exit(0);
    }
}
