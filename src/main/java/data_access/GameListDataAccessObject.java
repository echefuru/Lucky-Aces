package data_access;

import java.util.Set;

import use_case.gamelibrary.GameLibraryGameListDataAccessInterface;

/**
 * In-memory implementation of the DAO of the list of available games in the program.
 */
public class GameListDataAccessObject implements GameLibraryGameListDataAccessInterface {
    private final Set<String> allGames = Set.of("Blackjack", "Placeholder");

    @Override
    public boolean gameExists(String game) {
        return allGames.contains(game);
    }
}
