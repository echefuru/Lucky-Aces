package interface_adapter.game_library_select;

import interface_adapter.ViewManagerModel;
import use_case.game_search.GameSearchOutputBoundary;
import use_case.game_search.GameSearchOutputData;

/**
 * Presenter for the game search use case.
 */
public class GameSearchPresenter implements GameSearchOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final GameLibraryViewModel gameLibraryViewModel;

    public GameSearchPresenter(ViewManagerModel viewManagerModel, GameLibraryViewModel gameLibraryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameLibraryViewModel = gameLibraryViewModel;
    }

    @Override
    public void prepareSuccessView(GameSearchOutputData gameSearchOutputData) {
        final GameLibraryState gameLibraryState = gameLibraryViewModel.getState();
        final boolean[] gamesVisible = gameSearchOutputData.getGameVisible();

        gameLibraryState.setAvailableGamesVisible(gamesVisible);
        this.gameLibraryViewModel.setState(gameLibraryState);
        this.gameLibraryViewModel.firePropertyChanged("search");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // note: this use case currently can't fail
    }
}
