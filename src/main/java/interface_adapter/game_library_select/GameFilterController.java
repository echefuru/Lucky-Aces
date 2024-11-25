package interface_adapter.game_library_select;

import use_case.game_filter.GameFilterInputBoundary;
import use_case.game_filter.GameFilterInputData;

/**
 * Controller for the game filter use case.
 */
public class GameFilterController {
    private final GameFilterInputBoundary gameFilterInteractor;

    public GameFilterController(GameFilterInputBoundary gameFilterInteractor) {
        this.gameFilterInteractor = gameFilterInteractor;
    }

    /**
     * Executes the game filter Use Case and process the input data.
     * @param gameType the type that was selected.
     * @param playerCount the player count that was entered.
     */
    public void execute(String gameType, int playerCount) {
        final GameFilterInputData gameFilterInputData = new GameFilterInputData(gameType, playerCount);
        gameFilterInteractor.execute(gameFilterInputData);
    }
}
