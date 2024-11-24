package interface_adapter.gamelibrary;

/**
 * The State information representing the GameLibrary's state at the initial screen.
 */
public class GameLibraryState {

    private String[] availableGames;
    // private boolean[] availableGamesVisible;
    private String selectGameError;

    public String[] getAvailableGames() {
        return this.availableGames;
    }

    public void setAvailableGames(String[] availableGames) {
        this.availableGames = availableGames;
    }

    // What's the point of this?
//    public boolean[] getAvailableGamesVisible() {
//        return this.availableGamesVisible;
//    }

//    public void setAvailableGamesVisible(boolean[] availableGamesVisible) {
//        this.availableGamesVisible = availableGamesVisible;
//    }

    public String getSelectGameError() {
        return selectGameError;
    }

    public void setSelectGameError(String selectGameError) {
        this.selectGameError = selectGameError;
    }
}
