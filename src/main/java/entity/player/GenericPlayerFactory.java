package entity.player;

/**
 * Factory for creating CommonPlayer objects.
 */
public class GenericPlayerFactory implements PlayerFactory {

    @Override
    public Player create(String playerID, String type) {
        switch (type) {
            case "common":
                return new CommonPlayer(playerID);
            case "computer":
                return new ComputerPlayer(playerID);
            default:
                throw new IllegalArgumentException("Player type not supported");
        }
    }
}
