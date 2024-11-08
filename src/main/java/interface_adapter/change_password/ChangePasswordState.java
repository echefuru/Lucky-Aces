package interface_adapter.change_password;

/**
 * The state for the ChangePassword View Model.
 */
public class ChangePasswordState {
    private String playerID = "";

    private String password = "";
    private String passwordError;

    public ChangePasswordState(ChangePasswordState copy) {
        playerID = copy.playerID;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ChangePasswordState() {

    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPassword() {
        return password;
    }
}
