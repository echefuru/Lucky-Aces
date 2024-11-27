package use_case.game_search;

/**
 * Output Data for the Game Search Use Case.
 */
public class GameSearchOutputData {
    private final boolean[] gamesVisible;
    private final String searchText;

    public GameSearchOutputData(boolean[] gamesVisible, String searchText) {
        this.gamesVisible = gamesVisible;
        this.searchText = searchText;
    }

    public boolean[] getGameVisible() {
        return gamesVisible;
    }

    public String getSearchText() {
        return searchText;
    }
}
