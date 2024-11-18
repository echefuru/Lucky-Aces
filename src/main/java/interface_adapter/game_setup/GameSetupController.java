package interface_adapter.game_setup;

import use_case.game_setup.GameSetupInputBoundary;

/**
 * Controller for the game setup use case.
 */
public class GameSetupController {
    private final GameSetupInputBoundary gameSetupUseCaseInteractor;

    public GameSetupController(GameSetupInputBoundary gameSetupUseCaseInteractor) {
        this.gameSetupUseCaseInteractor = gameSetupUseCaseInteractor;
    }

    /**
     * Execute the game setup use case.
     */
    public void execute() {

    }

    /**
     * Switch to the game library view.
     */
    public void switchToGameLibraryView() {
        gameSetupUseCaseInteractor.switchToGameLibraryView();
    }
}
