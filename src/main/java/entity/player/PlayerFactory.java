package entity.player;

/**
 * Factory for creating users.
 */
public interface PlayerFactory {
    /**
     * Creates a new User.
     * @param playerID the name of the new user
     * @param password the password of the new user
     * @return the new user
     */
    Player create(String playerID, String password);

}
