package use_case.change_password;

/**
 * The input data for the Change Password Use Case.
 */
public class ChangePasswordInputData {

    private final String password;
    private final String playerID;

    public ChangePasswordInputData(String password, String playerID) {
        this.password = password;
        this.playerID = playerID;
    }

    String getPassword() {
        return password;
    }

    String getPlayerID() {
        return playerID;
    }

}
