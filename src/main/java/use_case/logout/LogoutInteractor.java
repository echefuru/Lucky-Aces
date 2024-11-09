package use_case.logout;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private LogoutUserDataAccessInterface userDataAccessObject;
    private LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutUserDataAccessInterface userDataAccessInterface,
                            LogoutOutputBoundary logoutOutputBoundary) {
        // DONE: save the DAO and Presenter in the instance variables.
        // Which parameter is the DAO and which is the presenter?
        this.userDataAccessObject = userDataAccessInterface;
        this.logoutPresenter = logoutOutputBoundary;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        // DONE: implement the logic of the Logout Use Case (depends on the LogoutInputData.java TODO)
        // * get the playerID out of the input data,
        final String playerID = logoutInputData.getPlayerID();
        // * set the playerID to null in the DAO
        userDataAccessObject.setCurrentPlayerID(null);
        // * instantiate the `LogoutOutputData`, which needs to contain the playerID.
        final LogoutOutputData logoutOutputData = new LogoutOutputData(playerID, false);
        // * tell the presenter to prepare a success view.
        logoutPresenter.prepareSuccessView(logoutOutputData);
    }
}
