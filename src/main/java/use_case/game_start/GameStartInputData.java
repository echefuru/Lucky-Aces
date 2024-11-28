package use_case.game_start;

import org.json.JSONObject;

/**
 * The input data for the Start Game Use Case.
 */
public class GameStartInputData {
    private final String selectedGame;
    private final JSONObject config;

    public GameStartInputData(String selectedGame, JSONObject config) {
        this.selectedGame = selectedGame;
        this.config = config;
    }

    public String getSelectedGame() {
        return selectedGame;
    }

    public JSONObject getConfig() {
        return config;
    }
}
