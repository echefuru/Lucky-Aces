package use_case.gamelibrary;

import entity.game_info.GameInfo;

/**
 * Data access for the game library use case.
 */
public interface GameLibraryGameInfoDataAccessInterface {

    /**
     * Check if the given game is available in the program.
     * @param game id of the game.
     * @return a GameInfo object containing the game information.
     */
    GameInfo getGame(String game);
}
