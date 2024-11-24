package use_case.gamelibrary;

import use_case.GameLibraryGameInfoDataAccessInterface;

/**
 * The game library Interactor.
 */
public class GameLibraryInteractor implements GameLibraryInputBoundary {

    private final GameLibraryOutputBoundary gameLibraryPresenter;
    private final GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject;

    public GameLibraryInteractor(GameLibraryOutputBoundary gameLibraryPresenter,
                                 GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject) {
        this.gameLibraryPresenter = gameLibraryPresenter;
        this.gameInfoDataAccessObject = gameInfoDataAccessObject;
    }

    @Override
    public void execute(GameLibraryInputData gameLibraryInputData) {
        final String selectedGame = gameLibraryInputData.getSelectedGame();

        if (gameInfoDataAccessObject.isAvailable(selectedGame)) {
            final String gameName = gameInfoDataAccessObject.getName(selectedGame);
            final String gameDescription = gameInfoDataAccessObject.getDescription(selectedGame);
            final GameLibraryOutputData gameLibraryOutputData = new GameLibraryOutputData(gameName, gameDescription);
            gameLibraryPresenter.prepareSuccessView(gameLibraryOutputData);
        }
        else {
            gameLibraryPresenter.prepareFailView("Game not available yet, please stay tuned!");
        }
    }

//    @Override
//    public void switchToChangePasswordView(ChangePasswordState changePasswordState) {
//        gameLibraryPresenter.switchToChangePasswordView(changePasswordState);
//    }

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
