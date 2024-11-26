package interface_adapter.game_library_select;

/**
 * The State information representing the GameLibrary's state at the initial screen.
 */
public class GameLibraryState {

    private String[] availableGames;
    private boolean[] availableGamesVisible;
    private String selectGameError;
    private String filterMessage;

    private String[] gameTypes = {};

    public String[] getAvailableGames() {
        return this.availableGames;
    }

    public void setAvailableGames(String[] availableGames) {
        this.availableGames = availableGames;
    }

    public void setAvailableGamesVisible(boolean[] availableGamesVisible) {
        this.availableGamesVisible = availableGamesVisible;
    }

    public boolean[] getAvailableGamesVisible() {
        return this.availableGamesVisible;
    }

    public String getSelectGameError() {
        return selectGameError;
    }

    public void setSelectGameError(String selectGameError) {
        this.selectGameError = selectGameError;
    }

    public void setGameTypes(String[] gameTypes) {
        this.gameTypes = gameTypes;
    }

    public String[] getGameTypes() {
        return this.gameTypes;
    }

    public String getFilterMessage() {
        return filterMessage;
    }

    public void setFilterMessage(String filterMessage) {
        this.filterMessage = filterMessage;
    }
}
