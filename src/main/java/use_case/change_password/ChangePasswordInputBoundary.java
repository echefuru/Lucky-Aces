package use_case.change_password;

import interface_adapter.gamelibrary.GameLibraryState;

/**
 * The Change Password Use Case.
 */
public interface ChangePasswordInputBoundary {

    /**
     * Execute the Change Password Use Case.
     * @param changePasswordInputData the input data for this use case
     */
    void execute(ChangePasswordInputData changePasswordInputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();

    /**
     * Executes the switch to game library view use case.
     * @param gameLibraryState current player
     */
    void switchToGameLibraryView(GameLibraryState gameLibraryState);
}
