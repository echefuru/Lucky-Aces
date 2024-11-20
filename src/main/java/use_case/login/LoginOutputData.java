package use_case.login;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String playerID;
    private final boolean useCaseFailed;
    private final String[] availableGames;
    private final boolean[] availableGamesVisible;

    public LoginOutputData(String playerID, boolean useCaseFailed, String[] availableGames,
                           boolean[] availableGamesVisible) {
        this.playerID = playerID;
        this.useCaseFailed = useCaseFailed;
        this.availableGames = availableGames;
        this.availableGamesVisible = availableGamesVisible;
    }

    public String getPlayerID() {
        return playerID;
    }

    public String[] getAvailableGames() {
        return availableGames;
    }

    public boolean[] getAvailableGamesVisible() {
        return availableGamesVisible;
    }
}
