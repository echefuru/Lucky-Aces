package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.gamelibrary.GameLibraryState;
import interface_adapter.gamelibrary.GameLibraryViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private GameLibraryViewModel gameLibraryViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                          GameLibraryViewModel gameLibraryViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameLibraryViewModel = gameLibraryViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        // We need to switch to the login view, which should have
        // an empty playerID and password.

        // We also need to set the playerID in the LoggedInState to
        // the empty string.

        // 1. get the LoggedInState out of the appropriate View Model,
        final GameLibraryState gameLibraryState = gameLibraryViewModel.getState();
        // 2. set the playerID in the state to the empty string
        // gameLibraryState.setPlayerID("");
        // 3. set the state in the LoggedInViewModel to the updated state
        gameLibraryViewModel.setState(gameLibraryState);
        // 4. firePropertyChanged so that the View that is listening is updated.
        gameLibraryViewModel.firePropertyChanged();

        // 5. get the LoginState out of the appropriate View Model,
        final LoginState loginState = loginViewModel.getState();
        // 6. set the playerID and password in the state to the empty string
        loginState.setPlayerID("");
        // 7. set the state in the LoginViewModel to the updated state
        loginViewModel.setState(loginState);
        // 8. firePropertyChanged so that the View that is listening is updated.
        loginViewModel.firePropertyChanged();

        // This code tells the View Manager to switch to the LoginView.
        this.viewManagerModel.setState(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // No need to add code here. We'll assume that logout can't fail.
        // Thought question: is this a reasonable assumption?
    }
}
