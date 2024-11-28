package use_case.game_select;

import org.json.JSONObject;

/**
 * Output Data for the select game use case.
 */
public class GameSelectOutputData {
    private final String selectedGame;
    private final String gameName;
    private final String gameDescription;
    private final String gameRules;
    private final JSONObject gameDefaultConfig;

    public GameSelectOutputData(String selectedGame, String gameName, String gameDescription, String gameRules,
                                JSONObject gameDefaultConfig) {
        this.selectedGame = selectedGame;
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.gameRules = gameRules;
        this.gameDefaultConfig = gameDefaultConfig;
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

    public JSONObject getGameDefaultConfig() {
        return gameDefaultConfig;
    }
}
