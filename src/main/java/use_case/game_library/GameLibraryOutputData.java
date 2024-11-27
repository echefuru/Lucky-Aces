package use_case.game_library;

/**
 * Output Data for the Initialization Use Case.
 */
public class GameLibraryOutputData {

    private final String[] games;
    private final String[] gameTypes;

    public GameLibraryOutputData(String[] games, String[] gameTypes) {
        this.games = games;
        this.gameTypes = gameTypes;
    }

    public String[] getGames() {
        return games;
    }

    public String[] getGameTypes() {
        return gameTypes;
    }
}
