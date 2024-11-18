package use_case.gamelibrary;

/**
 * Output Data for the game library Use Case.
 */
public class GameLibraryOutputData {
    private final String gameName;
    private final String gameDescription;

    public GameLibraryOutputData(String gameName, String gameDescription) {
        this.gameName = gameName;
        this.gameDescription = gameDescription;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameDescription() {
        return gameDescription;
    }
}
