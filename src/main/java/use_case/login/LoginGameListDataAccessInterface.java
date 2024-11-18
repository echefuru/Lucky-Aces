package use_case.login;

/**
 * DAO for accessing the game list.
 */
public interface LoginGameListDataAccessInterface {
    /**
     * Return the list of available games in the program.
     * @return array of the names of the available games.
     */
    String[] getAvailableGames();
}
