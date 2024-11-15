package entity.player;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonPlayerFactory implements PlayerFactory {

    @Override
    public Player create(String playerID, String password) {
        return new CommonPlayer(playerID, password);
    }
}
