package interface_adapter.game_setup;

import org.json.JSONObject;

import use_case.game_start.GameStartInputBoundary;
import use_case.game_start.GameStartInputData;

/**
 * Controller for the Game Start Use Case.
 */
public class GameStartController {
    private final GameStartInputBoundary gameStartUseCaseInteractor;

    public GameStartController(GameStartInputBoundary gameStartUseCaseInteractor) {
        this.gameStartUseCaseInteractor = gameStartUseCaseInteractor;
    }

    /**
     * Execute the Game Start Use Case.
     * @param selectedGame the selected game
     * @param config the configuration used to start the game
     */
    public void execute(String selectedGame, JSONObject config) {
        final GameStartInputData gameStartInputData = new GameStartInputData(selectedGame, config);
        gameStartUseCaseInteractor.execute(gameStartInputData);
    }
}
