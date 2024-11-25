package use_case.game_search;

import use_case.GameLibraryGameInfoDataAccessInterface;

/**
 * The game search Interactor.
 */
public class GameSearchInteractor implements GameSearchInputBoundary {

    private final GameSearchOutputBoundary gameSearchPresenter;
    private final GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject;

    public GameSearchInteractor(GameSearchOutputBoundary gameSearchPresenter,
                                GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject) {
        this.gameSearchPresenter = gameSearchPresenter;
        this.gameInfoDataAccessObject = gameInfoDataAccessObject;
    }

    @Override
    public void execute(GameSearchInputData gameSearchInputData) {
        final String selectedGame = gameSearchInputData.getSearchInput();

        final String[] availableGames = gameInfoDataAccessObject.getAvailableGames();
        final boolean[] availableGamesVisible = new boolean[availableGames.length];
        for (int i = 0; i < availableGames.length; i++) {
            availableGamesVisible[i] = availableGames[i].toLowerCase().contains(selectedGame.toLowerCase());
        }

        final GameSearchOutputData outputData = new GameSearchOutputData(availableGamesVisible);
        gameSearchPresenter.prepareSuccessView(outputData);
    }
}