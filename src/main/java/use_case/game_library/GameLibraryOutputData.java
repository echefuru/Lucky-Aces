package use_case.game_library;

/**
 * Output Data for the Initialization Use Case.
 */
public class GameLibraryOutputData {

    private final String[] games;

    public GameLibraryOutputData(String[] games) {
        this.games = games;
    }

    public String[] getGames() {
        return games;
    }
}
