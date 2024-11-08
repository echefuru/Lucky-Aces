package interface_adapter.gamelibrary;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import use_case.change_password.ChangePasswordOutputData;
import use_case.gamelibrary.GameLibraryOutputBoundary;

/**
 * The Presenter for the Game library Use Case.
 */
public class GameLibraryPresenter implements GameLibraryOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ChangePasswordViewModel changePasswordViewModel;

    public GameLibraryPresenter(ViewManagerModel viewManagerModel,
                                ChangePasswordViewModel changePasswordViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.changePasswordViewModel = changePasswordViewModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {

    }

    @Override
    public void prepareFailView(String message) {

    }

    @Override
    public void switchToChangePasswordView(ChangePasswordState changePasswordState) {
        this.changePasswordViewModel.setState(changePasswordState);
        this.changePasswordViewModel.firePropertyChanged();

        viewManagerModel.setState(changePasswordViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
