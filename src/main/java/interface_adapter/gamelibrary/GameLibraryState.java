package interface_adapter.gamelibrary;

/**
 * The State information representing the logged-in user at the game library screen.
 */
public class GameLibraryState {
    private String playerID = "";

    private String[] availableGames = {};

    private String selectGameError;

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setSelectGameError(String selectGameError) {
        this.selectGameError = selectGameError;
    }

    public void setAvailableGames(String[] availableGames) {
        this.availableGames = availableGames;
    }

    public String[] getAvailableGames() {
        return this.availableGames;
    }

    public String getSelectGameError() {
        return selectGameError;
    }
}
