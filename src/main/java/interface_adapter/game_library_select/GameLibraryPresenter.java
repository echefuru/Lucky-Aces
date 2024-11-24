package interface_adapter.game_library_select;

import interface_adapter.ViewManagerModel;
import use_case.game_library.GameLibraryOutputBoundary;
import use_case.game_library.GameLibraryOutputData;

/**
 * The Presenter for the Initialization Use Case.
 */
public class GameLibraryPresenter implements GameLibraryOutputBoundary {

    private final GameLibraryViewModel gameLibraryViewModel;
    private final ViewManagerModel viewManagerModel;

    public GameLibraryPresenter(GameLibraryViewModel gameLibraryViewModel,
                                ViewManagerModel viewManagerModel) {
        this.gameLibraryViewModel = gameLibraryViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(GameLibraryOutputData outputData) {
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

    @Override
    public void search(boolean[] availableGamesVisible) {
        final GameLibraryState gameLibraryState = gameLibraryViewModel.getState();
        gameLibraryState.setAvailableGamesVisible(availableGamesVisible);

        this.gameLibraryViewModel.setState(gameLibraryState);
        this.gameLibraryViewModel.firePropertyChanged("search");
    }
}
