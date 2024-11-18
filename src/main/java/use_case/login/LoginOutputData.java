package use_case.login;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String playerID;
    private final boolean useCaseFailed;
    private final String[] availableGames;

    public LoginOutputData(String playerID, boolean useCaseFailed, String[] availableGames) {
        this.playerID = playerID;
        this.useCaseFailed = useCaseFailed;
        this.availableGames = availableGames;
    }

    public String getPlayerID() {
        return playerID;
    }

    public String[] getAvailableGames() {
        return availableGames;
    }

}
