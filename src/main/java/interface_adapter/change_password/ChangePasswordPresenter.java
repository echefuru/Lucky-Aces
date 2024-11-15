package interface_adapter.change_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.gamelibrary.GameLibraryState;
import interface_adapter.gamelibrary.GameLibraryViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final ChangePasswordViewModel changePasswordViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final GameLibraryViewModel gameLibraryViewModel;

    public ChangePasswordPresenter(ViewManagerModel viewManagerModel,
                                   ChangePasswordViewModel changePasswordViewModel,
                                   LoginViewModel loginViewModel,
                                   GameLibraryViewModel gameLibraryViewModel) {
        this.changePasswordViewModel = changePasswordViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.gameLibraryViewModel = gameLibraryViewModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        // On success, switch to the login view.
        changePasswordViewModel.firePropertyChanged("password");

        final LoginState loginState = loginViewModel.getState();
        loginState.setPlayerID(outputData.getPlayerID());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToGameLibraryView(GameLibraryState gameLibraryState) {
        this.gameLibraryViewModel.setState(gameLibraryState);
        this.gameLibraryViewModel.firePropertyChanged();

        viewManagerModel.setState(gameLibraryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
