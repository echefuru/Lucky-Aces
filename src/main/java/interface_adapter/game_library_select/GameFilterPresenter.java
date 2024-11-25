package interface_adapter.game_library_select;

import interface_adapter.ViewManagerModel;
import use_case.game_filter.GameFilterOutputBoundary;
import use_case.game_filter.GameFilterOutputData;

/**
 * Presenter for the game filter use case.
 */
public class GameFilterPresenter implements GameFilterOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final GameLibraryViewModel gameLibraryViewModel;

    public GameFilterPresenter(ViewManagerModel viewManagerModel, GameLibraryViewModel gameLibraryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameLibraryViewModel = gameLibraryViewModel;
    }

    @Override
    public void prepareSuccessView(GameFilterOutputData gameFilterOutputData) {
        if (gameFilterOutputData.getGameTypes().length != 0) {
            final String[] gameTypes = gameFilterOutputData.getGameTypes();
            final GameLibraryState gameLibraryState = gameLibraryViewModel.getState();
            gameLibraryState.setGameTypes(gameTypes);

            this.gameLibraryViewModel.setState(gameLibraryState);
            this.gameLibraryViewModel.firePropertyChanged("filter");
        }
        else {
            final boolean[] availableGamesVisible = gameFilterOutputData.getGamesVisible();
            final GameLibraryState gameLibraryState = gameLibraryViewModel.getState();
            gameLibraryState.setAvailableGamesVisible(availableGamesVisible);

            this.gameLibraryViewModel.setState(gameLibraryState);
            this.gameLibraryViewModel.firePropertyChanged("search");
        }
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // note: this use case currently can't fail
    }
}
