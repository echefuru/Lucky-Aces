package entity.player;

/**
 * Factory for creating users.
 */
public interface PlayerFactory {
    /**
     * Creates a new User.
     * @param playerID the name of the new user
     * @param type the type of the player to be created, "common" or "computer"
     * @return the new user
     */
    Player create(String playerID, String type);

}
