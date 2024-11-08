package interface_adapter.gamelibrary;

import interface_adapter.change_password.ChangePasswordState;
import use_case.gamelibrary.GameLibraryInputBoundary;

/**
 * Controller for the game library Use Case.
 */
public class GameLibraryController {
    private final GameLibraryInputBoundary gamelibraryUseCaseInteractor;

    public GameLibraryController(GameLibraryInputBoundary gamelibraryUseCaseInteractor) {
        this.gamelibraryUseCaseInteractor = gamelibraryUseCaseInteractor;
    }

    /**
     * Executes the game library Use Case.
     */
    public void execute() {

    }

    /**
     * Executes the "switch to ChangePasswordView" Use Case.
     * @param changePasswordState current player
     */
    public void switchToChangePasswordView(ChangePasswordState changePasswordState) {
        gamelibraryUseCaseInteractor.switchToChangePasswordView(changePasswordState);
    }
}
