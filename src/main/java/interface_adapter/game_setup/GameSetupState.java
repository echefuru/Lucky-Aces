package interface_adapter.game_setup;

/**
 * The state for the game setup view model.
 */
public class GameSetupState {
    private String selectedGame;
    private String gameName;
    private String gameDescription;
    private String gameRules;

    public void setSelectedGame(String selectedGame) {
        this.selectedGame = selectedGame;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public String getSelectedGame() {
        return selectedGame;
    }

    public void setGameRules(String gameRules) {
        this.gameRules = gameRules;
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
