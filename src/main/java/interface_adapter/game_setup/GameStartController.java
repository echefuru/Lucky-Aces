package interface_adapter.game_setup;

import use_case.game_start.GameStartInputBoundary;

/**
 * Controller for the game setup use case.
 */
public class GameStartController {
    private final GameStartInputBoundary gameSetupUseCaseInteractor;

    public GameStartController(GameStartInputBoundary gameSetupUseCaseInteractor) {
        this.gameSetupUseCaseInteractor = gameSetupUseCaseInteractor;
    }

    /**
     * Execute the game setup use case.
     */
    public void execute() {

    }
}
