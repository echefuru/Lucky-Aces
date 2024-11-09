package use_case.logout;

/**
 * DAO for the Logout Use Case.
 */
public interface LogoutUserDataAccessInterface {

    /**
     * Returns the playerID of the curren user of the application.
     * @return the playerID of the current player
     */
    String getCurrentPlayerID();

    /**
     * Sets the playerID indicating who is the current player of the application.
     * @param playerID the new current playerID
     */
    void setCurrentPlayerID(String playerID);
}
