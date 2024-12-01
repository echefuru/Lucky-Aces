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
    public void execute(GameStartInputData gameStartInputData) {
        final String selectedGame = gameStartInputData.getSelectedGame();

        final GameStartOutputData gameStartOutputData = new GameStartOutputData(selectedGame);

        if ("blackjack".equals(selectedGame)) {
            gameSetupPresenter.prepareSuccessView(gameStartOutputData);
        }
        else {
            gameSetupPresenter.prepareFailView("Can't find selected game in program");
        }
    }
}
