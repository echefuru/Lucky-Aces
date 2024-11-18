package use_case.gamelibrary;

import data_access.GameListDataAccessObject;
import entity.game_info.GameInfo;
import entity.game_info.GameInfoFactory;
import interface_adapter.change_password.ChangePasswordState;

/**
 * The game library Interactor.
 */
public class GameLibraryInteractor implements GameLibraryInputBoundary {

    private final GameLibraryOutputBoundary gameLibraryPresenter;
    private final GameListDataAccessObject gameListDataAccessObject;
    private final GameInfoFactory gameInfoFactory;

    public GameLibraryInteractor(GameLibraryOutputBoundary gameLibraryPresenter,
                                 GameListDataAccessObject gameListDataAccessObject,
                                 GameInfoFactory gameInfoFactory) {
        this.gameLibraryPresenter = gameLibraryPresenter;
        this.gameListDataAccessObject = gameListDataAccessObject;
        this.gameInfoFactory = gameInfoFactory;
    }

    @Override
    public void execute(GameLibraryInputData gameLibraryInputData) {
        final String selectedGame = gameLibraryInputData.getSelectedGame();
        final GameInfo game = gameInfoFactory.create(selectedGame);
        if (game != null) {
            final String gameName = game.getName();
            final String gameDescription = game.getDescription();
            final GameLibraryOutputData gameLibraryOutputData = new GameLibraryOutputData(gameName, gameDescription);
            gameLibraryPresenter.prepareSuccessView(gameLibraryOutputData);
        }
        else {
            gameLibraryPresenter.prepareFailView("Not available yet. Please stay tuned!");
        }

    }

    @Override
    public void switchToChangePasswordView(ChangePasswordState changePasswordState) {
        gameLibraryPresenter.switchToChangePasswordView(changePasswordState);
    }
}
