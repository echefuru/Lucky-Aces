package use_case.game_select;

import use_case.GameLibraryGameInfoDataAccessInterface;

/**
 * The game library Interactor.
 */
public class GameSelectInteractor implements GameSelectInputBoundary {

    private final GameSelectOutputBoundary gameSelectPresenter;
    private final GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject;

    public GameSelectInteractor(GameSelectOutputBoundary gameSelectPresenter,
                                GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject) {
        this.gameSelectPresenter = gameSelectPresenter;
        this.gameInfoDataAccessObject = gameInfoDataAccessObject;
    }

    @Override
    public void execute(GameSelectInputData gameSelectInputData) {
        final String selectedGame = gameSelectInputData.getSelectedGame();

        if (gameInfoDataAccessObject.isAvailable(selectedGame)) {
            final String gameName = gameInfoDataAccessObject.getName(selectedGame);
            final String gameDescription = gameInfoDataAccessObject.getDescription(selectedGame);
            final GameSelectOutputData gameSelectOutputData = new GameSelectOutputData(gameName, gameDescription);
            gameSelectPresenter.prepareSuccessView(gameSelectOutputData);
        }
        else {
            gameSelectPresenter.prepareFailView("Game not available yet, please stay tuned!");
        }
    }
}
