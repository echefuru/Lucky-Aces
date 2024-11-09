package interface_adapter.login;

/**
 * The state for the Login View Model.
 */
public class LoginState {
    private String playerID = "";
    private String loginError;
    private String password = "";

    public String getPlayerID() {
        return playerID;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getPassword() {
        return password;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setLoginError(String playerIDError) {
        this.loginError = playerIDError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
