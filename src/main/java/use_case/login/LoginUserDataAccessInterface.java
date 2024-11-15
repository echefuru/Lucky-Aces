package use_case.login;

import entity.player.Player;

/**
 * DAO for the Login Use Case.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Checks if the given playerID exists.
     * @param playerID the playerID to look for
     * @return true if a user with the given playerID exists; false otherwise
     */
    boolean existsByName(String playerID);

    /**
     * Saves the user.
     * @param player the user to save
     */
    void save(Player player);

    /**
     * Returns the user with the given playerID.
     * @param playerID the playerID to look up
     * @return the player with the given playerID
     */
    Player get(String playerID);

    /**
     * Returns the playerID of the curren user of the application.
     * @return the playerID of the current user; null indicates that no one is logged into the application.
     */
    String getCurrentPlayerID();

    /**
     * Sets the playerID indicating who is the current user of the application.
     * @param playerID the new current playerID; null to indicate that no one is currently logged into the application.
     */
    void setCurrentPlayerID(String playerID);
}
