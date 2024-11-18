package entity.game_info;

/**
 * Creates GameInfo instances.
 */
public class GameInfoFactory {

    /**
     * Create an instance of the correct GameInfo.
     * @param name The name of the card game
     * @return An object containing the info of the game, null if the game corresponding to name doesn't exist
     */
    public GameInfo create(String name) {
        GameInfo gameInfo = null;
        if ("Blackjack".equals(name)) {
            gameInfo = new BlackjackGameInfo();
        }

        return gameInfo;
    }
}
