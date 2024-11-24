package use_case.game_select;

/**
 * Output Data for the game library Use Case.
 */
public class GameSelectOutputData {
    private final String gameName;
    private final String gameDescription;

    public GameSelectOutputData(String gameName, String gameDescription) {
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
