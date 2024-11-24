package interface_adapter.game_library_select;

import use_case.game_select.GameSelectInputBoundary;
import use_case.game_select.GameSelectInputData;

/**
 * Controller for the game library Use Case.
 */
public class GameSelectController {
    private final GameSelectInputBoundary gameSelectInteractor;

    public GameSelectController(GameSelectInputBoundary gameSelectInteractor) {
        this.gameSelectInteractor = gameSelectInteractor;
    }

    /**
     * Executes the game library Use Case.
     * @param selectedGame the names of the available games to select from
     */
    public void execute(String selectedGame) {
        final GameSelectInputData gameSelectInputData = new GameSelectInputData(selectedGame);
        gameSelectInteractor.execute(gameSelectInputData);
    }
}
