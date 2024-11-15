package entity.player;

/**
 * A simple implementation of the User interface.
 */
public class CommonPlayer implements Player {

    private final String playerID;
    private final String password;

    public CommonPlayer(String playerID, String password) {
        this.playerID = playerID;
        this.password = password;
    }

    @Override
    public String getPlayerID() {
        return playerID;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
