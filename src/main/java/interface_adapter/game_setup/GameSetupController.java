package interface_adapter.game_setup;

import use_case.game_setup.GameSetupInputBoundary;
import use_case.game_setup.GameSetupOutputBoundary;

/**
 * Controller for the game setup use case.
 */
public class GameSetupController implements GameSetupOutputBoundary {
    private final GameSetupInputBoundary gameSetupUseCaseInteractor;

    public GameSetupController(GameSetupInputBoundary gameSetupUseCaseInteractor) {
        this.gameSetupUseCaseInteractor = gameSetupUseCaseInteractor;
    }

    @Override
    public void prepareSuccessView() {

    }

    @Override
    public void prepareFailView() {

    }

    public void switchToGameLibraryView() {
        gameSetupUseCaseInteractor.switchToGameLibraryView();
    }
}
