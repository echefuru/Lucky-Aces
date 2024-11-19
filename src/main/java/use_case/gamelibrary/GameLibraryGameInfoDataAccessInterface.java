package use_case.gamelibrary;

/**
 * Data access for the game library use case.
 */
public interface GameLibraryGameInfoDataAccessInterface {

    /**
     * Retrieve the name of the given game.
     * @param game id of the game
     * @return the name of the game
     */
    String getName(String game);

    /**
     * Retrieve the description of the given game.
     * @param game id of the game
     * @return the game's description
     */
    String getDescription(String game);

    /**
     * Check if the given game is available in the program.
     * @param game id of the game.
     * @return true if and only if the game is ready to be played.
     */
    boolean isAvailable(String game);
}
