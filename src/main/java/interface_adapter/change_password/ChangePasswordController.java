package interface_adapter.change_password;

import interface_adapter.gamelibrary.GameLibraryState;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class ChangePasswordController {
    private final ChangePasswordInputBoundary playerIDChangePasswordUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary playerIDChangePasswordUseCaseInteractor) {
        this.playerIDChangePasswordUseCaseInteractor = playerIDChangePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     * @param password the new password
     * @param playerID the user whose password to change
     */
    public void execute(String password, String playerID) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(playerID, password);

        playerIDChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }

    /**
     * Executes the "switch to ChangePasswordView" Use Case.
     * @param gameLibraryState current player
     */
    public void switchToGameLibraryView(GameLibraryState gameLibraryState) {
        playerIDChangePasswordUseCaseInteractor.switchToGameLibraryView(gameLibraryState);
    }
}
