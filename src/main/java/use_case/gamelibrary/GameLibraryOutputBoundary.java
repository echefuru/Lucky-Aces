package use_case.gamelibrary;

import interface_adapter.change_password.ChangePasswordState;
import use_case.change_password.ChangePasswordOutputData;

/**
 * The output boundary for the Logged In Use Case.
 */
public interface GameLibraryOutputBoundary {

    /**
     * Prepares the success view for the game library Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(GameLibraryOutputData outputData);

    /**
     * Prepares the failure view for the game library Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switch to Change password view use case.
     * @param changePasswordState current player
     */
    void switchToChangePasswordView(ChangePasswordState changePasswordState);
}
