package use_case.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {
    private String playerID;

    public LogoutInputData(String playerID) {
        // DONE: save the current playerID in an instance variable and add a getter.
        this.playerID = playerID;
    }

    public String getPlayerID() {
        return playerID;
    }
}
