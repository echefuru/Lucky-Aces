package use_case.gamelibrary;

import interface_adapter.change_password.ChangePasswordState;

/**
 * The game library Interactor.
 */
public class GameLibraryInteractor implements GameLibraryInputBoundary {

    private final GameLibraryOutputBoundary gamePresenter;

    public GameLibraryInteractor(GameLibraryOutputBoundary gamePresenter) {
        this.gamePresenter = gamePresenter;
    }

    @Override
    public void execute(GameLibraryInputData gameLibraryInputData) {
        final String selectedGame = gameLibraryInputData.getSelectedGame();
        // TODO: Do something
        System.out.println("Selected game: " + selectedGame);
    }

    @Override
    public void switchToChangePasswordView(ChangePasswordState changePasswordState) {
        gamePresenter.switchToChangePasswordView(changePasswordState);
    }
}
