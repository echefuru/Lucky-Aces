package use_case.login;

/**
 * The Input Data for the Login Use Case.
 */
public class LoginInputData {

    private final String playerID;
    private final String password;

    public LoginInputData(String playerID, String password) {
        this.playerID = playerID;
        this.password = password;
    }

    String getPlayerID() {
        return playerID;
    }

    String getPassword() {
        return password;
    }

}
