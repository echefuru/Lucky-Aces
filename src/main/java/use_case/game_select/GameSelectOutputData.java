package use_case.game_select;

/**
 * Output Data for the select game use case.
 */
public class GameSelectOutputData {
    private final String selectedGame;
    private final String gameName;
    private final String gameDescription;
    private final String gameRules;

    public GameSelectOutputData(String selectedGame, String gameName, String gameDescription, String gameRules) {
        this.selectedGame = selectedGame;
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.gameRules = gameRules;
    }

    public String getSelectedGame() {
        return selectedGame;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public String getGameRules() {
        return gameRules;
    }
}
