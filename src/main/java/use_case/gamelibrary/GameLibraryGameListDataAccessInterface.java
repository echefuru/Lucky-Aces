package use_case.gamelibrary;

/**
 * Data access for the game library use case.
 */
public interface GameLibraryGameListDataAccessInterface {

    /**
     * Check if a game by the given name is available in the program.
     * @param game the name of the game.
     * @return true if and only if the game is available in the program.
     */
    boolean gameExists(String game);
}
