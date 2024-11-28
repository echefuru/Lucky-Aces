package interface_adapter.game_setup;

import org.json.JSONObject;

/**
 * The state for the game setup view model.
 */
public class GameSetupState {
    private String selectedGame;
    private String gameName;
    private String gameDescription;
    private String gameRules;
    private JSONObject gameConfig;

    public void setSelectedGame(String selectedGame) {
        this.selectedGame = selectedGame;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public void setGameRules(String gameRules) {
        this.gameRules = gameRules;
    }

    public void setGameConfig(JSONObject gameConfig) {
        this.gameConfig = gameConfig;
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

    public JSONObject getGameConfig() {
        return gameConfig;
    }

}
