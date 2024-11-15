package use_case.logout;

/**
 * Output Data for the Logout Use Case.
 */
public class LogoutOutputData {

    private String playerID;
    private boolean useCaseFailed;

    public LogoutOutputData(String playerID, boolean useCaseFailed) {
        // save the parameters in the instance variables.
        this.playerID = playerID;
        this.useCaseFailed = useCaseFailed;
    }

    public String getPlayerID() {
        return playerID;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
