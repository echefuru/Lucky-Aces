package interface_adapter.game_library_select;

import use_case.game_search.GameSearchInputBoundary;
import use_case.game_search.GameSearchInputData;

/**
 * Controller for the game search use case.
 */
public class GameSearchController {
    private final GameSearchInputBoundary gameSearchInteractor;

    public GameSearchController(GameSearchInputBoundary gameSearchInteractor) {
        this.gameSearchInteractor = gameSearchInteractor;
    }

    /**
     * Executes the game search Use Case.
     * @param searchText text that was entered
     */
    public void execute(String searchText) {
        final GameSearchInputData gameSearchInputData = new GameSearchInputData(searchText);
        gameSearchInteractor.execute(gameSearchInputData);
    }
}
