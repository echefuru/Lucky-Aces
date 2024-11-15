package interface_adapter.logout;

import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;

/**
 * The controller for the Logout Use Case.
 */
public class LogoutController {

    private LogoutInputBoundary logoutUseCaseInteractor;

    public LogoutController(LogoutInputBoundary logoutUseCaseInteractor) {
        // Save the interactor in the instance variable.
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }

    /**
     * Executes the Logout Use Case.
     * @param playerID the playerID of the user logging in
     */
    public void execute(String playerID) {
        // run the use case interactor for the logout use case
        // 1. instantiate the `LogoutInputData`, which should contain the playerID.
        // 2. tell the Interactor to execute.
        final LogoutInputData logoutInputData = new LogoutInputData(playerID);
        logoutUseCaseInteractor.execute(logoutInputData);
    }
}
