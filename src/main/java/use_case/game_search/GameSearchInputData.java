package use_case.game_search;

/**
 * The input data for the game search Use Case.
 */
public class GameSearchInputData {

    private final String searchInput;

    public GameSearchInputData(String searchInput) {
        this.searchInput = searchInput;
    }

    String getSearchInput() {
        return this.searchInput;
    }
}
