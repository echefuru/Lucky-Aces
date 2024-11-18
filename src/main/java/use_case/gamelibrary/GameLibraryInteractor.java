package use_case.gamelibrary;

import interface_adapter.change_password.ChangePasswordState;

/**
 * The game library Interactor.
 */
public class GameLibraryInteractor implements GameLibraryInputBoundary {

    private final GameLibraryOutputBoundary gameLibraryPresenter;
    private final GameLibraryGameListDataAccessInterface gameListDataAccessObject;

    public GameLibraryInteractor(GameLibraryOutputBoundary gameLibraryPresenter,
                                 GameLibraryGameListDataAccessInterface gameListDataAccessObject) {
        this.gameLibraryPresenter = gameLibraryPresenter;
        this.gameListDataAccessObject = gameListDataAccessObject;
    }

    @Override
    public void execute(GameLibraryInputData gameLibraryInputData) {
        final String selectedGame = gameLibraryInputData.getSelectedGame();
        if (gameListDataAccessObject.gameExists(selectedGame)) {
            final GameLibraryOutputData gameLibraryOutputData = new GameLibraryOutputData(selectedGame);
            gameLibraryPresenter.prepareSuccessView(gameLibraryOutputData);
        }
        else {
            gameLibraryPresenter.prepareFailView("Game not found.");
        }

    }

    @Override
    public void switchToChangePasswordView(ChangePasswordState changePasswordState) {
        gameLibraryPresenter.switchToChangePasswordView(changePasswordState);
    }
}
