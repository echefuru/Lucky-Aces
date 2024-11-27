package use_case.game_start;

/**
 * Interactor for the game setup use case.
 */
public class GameStartInteractor implements GameStartInputBoundary {
    private final GameStartOutputBoundary gameSetupPresenter;

    public GameStartInteractor(GameStartOutputBoundary gameSetupPresenter) {
        this.gameSetupPresenter = gameSetupPresenter;
    }

    @Override
    public void execute() {

    }
}
