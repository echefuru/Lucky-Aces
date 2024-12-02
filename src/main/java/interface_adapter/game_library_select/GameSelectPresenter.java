package interface_adapter.game_library_select;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_setup.GameSetupState;
import interface_adapter.game_setup.GameSetupViewModel;
import use_case.game_select.GameSelectOutputBoundary;
import use_case.game_select.GameSelectOutputData;

/**
 * The Presenter for the Game library Use Case.
 */
public class GameSelectPresenter implements GameSelectOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final GameSetupViewModel gameSetupViewModel;
    private final GameLibraryViewModel gameLibraryViewModel;

    public GameSelectPresenter(ViewManagerModel viewManagerModel,
                               GameSetupViewModel gameSetupViewModel,
                               GameLibraryViewModel gameLibraryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameSetupViewModel = gameSetupViewModel;
        this.gameLibraryViewModel = gameLibraryViewModel;
    }

    @Override
    public void prepareSuccessView(GameSelectOutputData outputData) {
        // On success, switch to the game setup view.
        final GameSetupState gameSetupState = gameSetupViewModel.getState();
        gameSetupState.setSelectedGame(outputData.getSelectedGame());
        gameSetupState.setGameName(outputData.getGameName());
        gameSetupState.setGameDescription(outputData.getGameDescription());
        gameSetupState.setGameRules(outputData.getGameRules());
        gameSetupState.setGameConfig(outputData.getGameDefaultConfig());
        this.gameSetupViewModel.setState(gameSetupState);
        this.gameSetupViewModel.firePropertyChanged("init");

        // Clears the error field in Game Library
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
}
