package interface_adapter.game_setup;

import interface_adapter.ViewManagerModel;
import interface_adapter.gamelibrary.GameLibraryViewModel;
import use_case.game_setup.GameSetupOutputBoundary;

/**
 * Presenter for the game setup use case.
 */
public class GameSetupPresenter implements GameSetupOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final GameLibraryViewModel gameLibraryViewModel;

    public GameSetupPresenter(ViewManagerModel viewManagerModel,
                              GameLibraryViewModel gameLibraryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameLibraryViewModel = gameLibraryViewModel;
    }

    @Override
    public void prepareSuccessView() {

    }

    @Override
    public void prepareFailView() {

    }

    @Override
    public void switchToGameLibraryView() {
        viewManagerModel.setState(this.gameLibraryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
