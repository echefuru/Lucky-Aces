package interface_adapter.gamelibrary;

/**
 * The State information representing the logged-in user.
 */
public class GameLibraryState {
    private String playerID = "";

    private String password = "";
    private String passwordError;

    public GameLibraryState(GameLibraryState copy) {
        playerID = copy.playerID;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public GameLibraryState() {

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
