package interface_adapter.gamelibrary;

/**
 * The State information representing the logged-in user at the game library screen.
 */
public class GameLibraryState {
    private String playerID = "";

    private String password = "";
    private String passwordError;

    // TODO: Find a better way to implement the list of games
    private String[] availableGames = {"Blackjack", "Placeholder"};

    public GameLibraryState(GameLibraryState copy) {
        this.playerID = copy.playerID;
        this.password = copy.password;
        this.passwordError = copy.passwordError;
        this.availableGames = copy.availableGames;
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

    public void setAvailableGames(String[] availableGames) {
        this.availableGames = availableGames;
    }

    public String getPassword() {
        return password;
    }

    public String[] getAvailableGames() {
        return this.availableGames;
    }
}
