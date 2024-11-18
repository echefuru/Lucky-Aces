package entity.player;

/**
 * Factory for creating Computer Player objects.
 */
public class ComputerPlayerFactory implements PlayerFactory {
    @Override
    public Player create(String playerID, String password) {
        return new ComputerPlayer(playerID);
    }
}
