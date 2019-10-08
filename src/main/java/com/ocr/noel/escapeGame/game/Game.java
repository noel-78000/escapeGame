package com.ocr.noel.escapeGame.game;

import com.ocr.noel.escapeGame.enums.GameChoiceEnum;
import com.ocr.noel.escapeGame.utils.ConfigUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game {
    private final static Logger log = LogManager.getLogger(Game.class);

    public Game() {
    }

    /**
     * The method to start the game and make choice of games
     */
    public void startGame() {
        log.info("Game start");
        choiceGameMode();
    }

    /**
     * Method to choice a mode of game or quit the program
     */
    private void choiceGameMode() {
        printChoiceModeOfGame();
        GameMode gameMode;
        while (ConfigUtil.getScannerIn().hasNext()) {
            String line = ConfigUtil.getScannerIn().nextLine().trim().toLowerCase();
            switch (line) {
                case "1":
                    gameMode = new GameChallenger();
                    do {
                        gameMode.startGame();
                    } while (gameMode.askIfReplayGame());
                    break;

                case "2":
                    gameMode = new GameDefender();
                    do {
                        gameMode.startGame();
                    } while (gameMode.askIfReplayGame());
                    break;

                case "3":
                    gameMode = new GameDuel();
                    do {
                        gameMode.startGame();
                    }
                    while (gameMode.askIfReplayGame());
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
     * Print into the console the possible choices of games or the choice to quit the program
     */
    private void printChoiceModeOfGame() {
        System.out.println("Choisir un mode de jeu:");
        System.out.println(String.format("%s - %s", GameChoiceEnum.CHALLENGER.getChoice(), GameChoiceEnum.CHALLENGER.getDescription()));
        System.out.println(String.format("%s - %s", GameChoiceEnum.DEFENDER.getChoice(), GameChoiceEnum.DEFENDER.getDescription()));
        System.out.println(String.format("%s - %s", GameChoiceEnum.DUEL.getChoice(), GameChoiceEnum.DUEL.getDescription()));
        System.out.println(String.format("%s - %s", GameChoiceEnum.GAME_LEAVE.getChoice(), GameChoiceEnum.GAME_LEAVE.getDescription()));
    }

    /**
     * This method close the console scanner and finish the program
     */
    private void stopGame() {
        System.out.println("Le jeu est terminé!");
        ConfigUtil.getScannerIn().close();
        log.info("end of program.");
        System.exit(0);
    }
}
