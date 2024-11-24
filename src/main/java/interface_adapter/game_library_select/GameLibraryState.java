package interface_adapter.game_library_select;

/**
 * The State information representing the GameLibrary's state at the initial screen.
 */
public class GameLibraryState {

    private String[] availableGames;
    private String selectGameError;

    public String[] getAvailableGames() {
        return this.availableGames;
    }

    public void setAvailableGames(String[] availableGames) {
        this.availableGames = availableGames;
    }

    public String getSelectGameError() {
        return selectGameError;
    }

    public void setSelectGameError(String selectGameError) {
        this.selectGameError = selectGameError;
    }
}
