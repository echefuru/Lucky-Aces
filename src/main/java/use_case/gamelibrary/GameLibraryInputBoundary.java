package use_case.gamelibrary;

import interface_adapter.change_password.ChangePasswordState;

/**
 * The Logged In Use Case.
 */
public interface GameLibraryInputBoundary {

    /**
     * Execute the game library Use Case.
     * @param gameLibraryInputData the input data for this use case
     */
    void execute(GameLibraryInputData gameLibraryInputData);

//    /**
//     * Executes the switch to change password view use case.
//     * @param changePasswordState current player
//     */
//    void switchToChangePasswordView(ChangePasswordState changePasswordState);

    /**
     * Executes the "search" Use Case.
     * @param info search input
     */
    void search(String info);
}
