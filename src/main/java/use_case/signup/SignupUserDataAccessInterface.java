package use_case.signup;

import entity.player.Player;

/**
 * DAO for the Signup Use Case.
 */
public interface SignupUserDataAccessInterface {

    /**
     * Checks if the given playerID exists.
     * @param playerID the playerID to look for
     * @return true if a player with the given playerID exists; false otherwise
     */
    boolean existsByName(String playerID);

    /**
     * Saves the user.
     * @param player the user to save
     */
    void save(Player player);
}
