package use_case.login;

/**
 * DAO for accessing the game list.
 */
public interface LoginGameInfoDataAccessInterface {
    /**
     * Return the list of available games in the program.
     * @return array of the ids of the available games.
     */
    String[] getAvailableGames();

    /**
     * Return the list of visible of available games' buttons in the program.
     * @return array of the booleans of the visible of available games' buttons.
     */
    boolean[] getAvailableGamesVisible();
}
