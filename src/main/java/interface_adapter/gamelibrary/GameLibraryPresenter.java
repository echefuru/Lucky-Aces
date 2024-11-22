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
    private final GameLibraryViewModel gameLibraryViewModel;

    public GameLibraryPresenter(ViewManagerModel viewManagerModel,
                                ChangePasswordViewModel changePasswordViewModel,
                                GameSetupViewModel gameSetupViewModel,
                                GameLibraryViewModel gameLibraryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.changePasswordViewModel = changePasswordViewModel;
        this.gameSetupViewModel = gameSetupViewModel;
        this.gameLibraryViewModel = gameLibraryViewModel;
    }

    @Override
    public void prepareSuccessView(GameLibraryOutputData outputData) {
        // On success, switch to the game setup view.
        final GameSetupState gameSetupState = gameSetupViewModel.getState();
        gameSetupState.setGameName(outputData.getGameName());
        gameSetupState.setGameDescription(outputData.getGameDescription());
        this.gameSetupViewModel.setState(gameSetupState);
        this.gameSetupViewModel.firePropertyChanged();

        final GameLibraryState gameLibraryState = gameLibraryViewModel.getState();
        gameLibraryState.setSelectGameError(null);
        this.gameLibraryViewModel.setState(gameLibraryState);
        this.gameLibraryViewModel.firePropertyChanged();

        this.viewManagerModel.setState(gameSetupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String message) {
        final GameLibraryState gameLibraryState = gameLibraryViewModel.getState();
        gameLibraryState.setSelectGameError(message);
        this.gameLibraryViewModel.setState(gameLibraryState);
        this.gameLibraryViewModel.firePropertyChanged();
    }

    @Override
    public void switchToChangePasswordView(ChangePasswordState changePasswordState) {
        this.changePasswordViewModel.setState(changePasswordState);
        this.changePasswordViewModel.firePropertyChanged();

        viewManagerModel.setState(changePasswordViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void search(boolean[] availableGamesVisible) {
        final GameLibraryState gameLibraryState = gameLibraryViewModel.getState();
        gameLibraryState.setAvailableGamesVisible(availableGamesVisible);

        this.gameLibraryViewModel.setState(gameLibraryState);
        this.gameLibraryViewModel.firePropertyChanged();
    }

    @Override
    public void executeFilter(String[] gameTypes) {
        final GameLibraryState gameLibraryState = gameLibraryViewModel.getState();
        gameLibraryState.setGameTypes(gameTypes);

        gameLibraryViewModel.firePropertyChanged("filter");
    }

    @Override
    public void filter(boolean[] availableGamesVisible) {
        final GameLibraryState gameLibraryState = gameLibraryViewModel.getState();
        gameLibraryState.setAvailableGamesVisible(availableGamesVisible);

        this.gameLibraryViewModel.setState(gameLibraryState);
        this.gameLibraryViewModel.firePropertyChanged();
    }
}
