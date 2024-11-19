package use_case.gamelibrary;

import data_access.GameInfoDataAccessObject;
import entity.game_info.GameInfo;
import interface_adapter.change_password.ChangePasswordState;

/**
 * The game library Interactor.
 */
public class GameLibraryInteractor implements GameLibraryInputBoundary {

    private final GameLibraryOutputBoundary gameLibraryPresenter;
    private final GameInfoDataAccessObject gameInfoDataAccessObject;

    public GameLibraryInteractor(GameLibraryOutputBoundary gameLibraryPresenter,
                                 GameInfoDataAccessObject gameInfoDataAccessObject) {
        this.gameLibraryPresenter = gameLibraryPresenter;
        this.gameInfoDataAccessObject = gameInfoDataAccessObject;
    }

    @Override
    public void execute(GameLibraryInputData gameLibraryInputData) {
        final String selectedGame = gameLibraryInputData.getSelectedGame();
        final GameInfo game = gameInfoDataAccessObject.getGame(selectedGame);

        final String gameName = game.getName();
        final String gameDescription = game.getDescription();
        final GameLibraryOutputData gameLibraryOutputData = new GameLibraryOutputData(gameName, gameDescription);
        gameLibraryPresenter.prepareSuccessView(gameLibraryOutputData);
    }

    @Override
    public void switchToChangePasswordView(ChangePasswordState changePasswordState) {
        gameLibraryPresenter.switchToChangePasswordView(changePasswordState);
    }
}
