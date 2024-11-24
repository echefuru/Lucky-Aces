package interface_adapter.gamelibrary;

import interface_adapter.ViewManagerModel;
import use_case.initialization.InitializationOutputBoundary;
import use_case.initialization.InitializationOutputData;

/**
 * The Presenter for the Initialization Use Case.
 */
public class InitializationPresenter implements InitializationOutputBoundary {

    private final GameLibraryViewModel gameLibraryViewModel;
    private final ViewManagerModel viewManagerModel;

    public InitializationPresenter(GameLibraryViewModel gameLibraryViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.gameLibraryViewModel = gameLibraryViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(InitializationOutputData outputData) {
        // On success, display GameLibraryView with available games.
        final GameLibraryState gameLibraryState = gameLibraryViewModel.getState();
        gameLibraryState.setAvailableGames(outputData.getGames());
        gameLibraryViewModel.setState(gameLibraryState);
        gameLibraryViewModel.firePropertyChanged();

        viewManagerModel.setState(gameLibraryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final GameLibraryState gameLibraryState = gameLibraryViewModel.getState();
        gameLibraryState.setSelectGameError(errorMessage);
        gameLibraryViewModel.setState(gameLibraryState);
        gameLibraryViewModel.firePropertyChanged();
    }
}
