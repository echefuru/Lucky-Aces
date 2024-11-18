package data_access;

import java.util.Arrays;
import java.util.Set;

import use_case.gamelibrary.GameLibraryGameListDataAccessInterface;
import use_case.login.LoginGameListDataAccessInterface;

/**
 * In-memory implementation of the DAO of the list of available games in the program.
 */
public class GameListDataAccessObject implements GameLibraryGameListDataAccessInterface,
                                                 LoginGameListDataAccessInterface {
    private final Set<String> allGames = Set.of("Blackjack", "Placeholder");
    private String[] availableGames;

    public GameListDataAccessObject() {
        availableGames = allGames.toArray(new String[0]);
        Arrays.sort(availableGames);
    }

    @Override
    public boolean gameExists(String game) {
        return allGames.contains(game);
    }

    @Override
    public String[] getAvailableGames() {
        return availableGames;
    }
}
