package use_case.game_library;

/**
 * Output Data for the Initialization Use Case.
 */
public class GameLibraryOutputData {

    private final String[] games;
    private final String[] gameTypes;
    private final String[] gameNames;

    public GameLibraryOutputData(String[] games, String[] gameTypes, String[] gameNames) {
        this.games = games;
        this.gameTypes = gameTypes;
        this.gameNames = gameNames;
    }

    public String[] getGames() {
        return games;
    }

    public String[] getGameTypes() {
        return gameTypes;
    }

    public String[] getGameNames() {
        return gameNames;
    }
}
