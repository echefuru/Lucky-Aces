package use_case.login;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String playerID;
    private final boolean useCaseFailed;

    public LoginOutputData(String playerID, boolean useCaseFailed) {
        this.playerID = playerID;
        this.useCaseFailed = useCaseFailed;
    }

    public String getPlayerID() {
        return playerID;
    }

}
