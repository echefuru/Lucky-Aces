package use_case.game_setup;

/**
 * Interactor for the game setup use case.
 */
public class GameSetupInteractor implements GameSetupInputBoundary {
    private final GameSetupOutputBoundary gameSetupPresenter;

    public GameSetupInteractor(GameSetupOutputBoundary gameSetupPresenter) {
        this.gameSetupPresenter = gameSetupPresenter;
    }

    @Override
    public void execute() {

    }

    @Override
    public void switchToGameLibraryView() {
        gameSetupPresenter.switchToGameLibraryView();
    }
}
