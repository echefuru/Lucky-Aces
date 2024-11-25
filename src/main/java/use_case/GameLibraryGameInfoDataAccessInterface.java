package use_case;

import org.json.JSONArray;

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

    /**
     * Return the list of available games in the program.
     * @return array of the ids of the available games.
     */
    String[] getAvailableGames();

    /**
     * Return the type of the given game in the program.
     * @param game id of the game.
     * @return array of the types of the given game.
     */
    JSONArray getType(String game);

    /**
     * Return the max_players of the given game in the program.
     * @param game id of the game.
     * @return the max_players of the given game in the program.
     */
    int getMaxPlayer(String game);

    /**
     * Return the min_players of the given game in the program.
     * @param game id of the game.
     * @return the min_players of the given game in the program.
     */
    int getMinPlayer(String game);
}
