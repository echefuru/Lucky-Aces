package interface_adapter.gamelibrary;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.game_setup.GameSetupState;
import interface_adapter.game_setup.GameSetupViewModel;
import use_case.gamelibrary.GameLibraryOutputBoundary;
import use_case.gamelibrary.GameLibraryOutputData;

/**
 * The Presenter for the Game library Use Case.
 */
public class GameLibraryPresenter implements GameLibraryOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final GameSetupViewModel gameSetupViewModel;

    public GameLibraryPresenter(ViewManagerModel viewManagerModel,
                                ChangePasswordViewModel changePasswordViewModel,
                                GameSetupViewModel gameSetupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.changePasswordViewModel = changePasswordViewModel;
        this.gameSetupViewModel = gameSetupViewModel;
    }

    @Override
    public void prepareSuccessView(GameLibraryOutputData outputData) {
        // On success, switch to the game setup view.
        final GameSetupState gameSetupState = gameSetupViewModel.getState();
        gameSetupState.setSelectedGame(outputData.getSelectedGame());
        this.gameSetupViewModel.setState(gameSetupState);
        this.gameSetupViewModel.firePropertyChanged();

        this.viewManagerModel.setState(gameSetupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
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
