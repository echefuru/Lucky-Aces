package use_case.login;

import entity.player.Player;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginGameInfoDataAccessInterface gameListDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary,
                           LoginGameInfoDataAccessInterface gameListDataAccessObject) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        this.gameListDataAccessObject = gameListDataAccessObject;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String playerID = loginInputData.getPlayerID();
        final String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(playerID)) {
            loginPresenter.prepareFailView(playerID + ": Account does not exist.");
        }
        else {
            final String pwd = userDataAccessObject.get(playerID).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + playerID + "\".");
            }
            else {

                final Player player = userDataAccessObject.get(loginInputData.getPlayerID());

                userDataAccessObject.setCurrentPlayerID(player.getPlayerID());
                final String[] availableGames = gameListDataAccessObject.getAvailableGames();
                final LoginOutputData loginOutputData = new LoginOutputData(player.getPlayerID(), false,
                        availableGames);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    @Override
    public void switchToSignupView() {
        loginPresenter.switchToSignupView();
    }
}
