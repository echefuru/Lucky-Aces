package interface_adapter.game_setup;

/**
 * The state for the game setup view model.
 */
public class GameSetupState {
    private String gameName = "";
    private String gameDescription = "";

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameDescription() {
        return gameDescription;
    }
}
