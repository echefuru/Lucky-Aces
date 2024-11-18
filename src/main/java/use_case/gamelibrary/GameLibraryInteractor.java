package use_case.gamelibrary;

import data_access.GameListDataAccessObject;
import interface_adapter.change_password.ChangePasswordState;

/**
 * The game library Interactor.
 */
public class GameLibraryInteractor implements GameLibraryInputBoundary {

    private final GameLibraryOutputBoundary gameLibraryPresenter;
    private final GameListDataAccessObject gameListDataAccessObject;

    public GameLibraryInteractor(GameLibraryOutputBoundary gameLibraryPresenter,
                                 GameListDataAccessObject gameListDataAccessObject) {
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
