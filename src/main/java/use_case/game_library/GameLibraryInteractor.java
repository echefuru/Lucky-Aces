package use_case.game_library;

import use_case.GameLibraryGameInfoDataAccessInterface;

/**
 * The interactor for the initialization use case.
 */
public class GameLibraryInteractor implements GameLibraryInputBoundary {

    private final GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject;
    private final GameLibraryOutputBoundary gameLibraryPresenter;

    public GameLibraryInteractor(GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject,
                                 GameLibraryOutputBoundary gameLibraryPresenter) {
        this.gameInfoDataAccessObject = gameInfoDataAccessObject;
        this.gameLibraryPresenter = gameLibraryPresenter;
    }

    @Override
    public void execute() {

        final String[] availableGames = gameInfoDataAccessObject.getAvailableGames();
        if (availableGames.length == 0) {
            gameLibraryPresenter.prepareFailView("No available games, check game_info.json.");
        }
        else {
            final GameLibraryOutputData outputData = new GameLibraryOutputData(gameInfoDataAccessObject
                    .getAvailableGames());
            gameLibraryPresenter.prepareSuccessView(outputData);
        }
    }

    @Override
    public void search(String info) {
        final String[] availableGames = gameInfoDataAccessObject.getAvailableGames();
        final boolean[] availableGamesVisible = new boolean[availableGames.length];
        for (int i = 0; i < availableGames.length; i++) {
            availableGamesVisible[i] = availableGames[i].toLowerCase().contains(info.toLowerCase());
        }
        gameLibraryPresenter.search(availableGamesVisible);
    }
}
