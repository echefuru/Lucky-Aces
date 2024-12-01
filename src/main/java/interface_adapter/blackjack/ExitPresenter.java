package interface_adapter.blackjack;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_library_select.GameLibraryViewModel;
import use_case.blackjack.exit.ExitOutputBoundary;

/**
 * The Presenter for the Exit Use Case for Blackjack.
 */
public class ExitPresenter implements ExitOutputBoundary {

    private final GameLibraryViewModel gameLibraryViewModel;
    private final ViewManagerModel viewManagerModel;

    public ExitPresenter(ViewManagerModel viewManagerModel,
                              BlackjackViewModel blackjackViewModel,
                              GameLibraryViewModel gameLibraryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameLibraryViewModel = gameLibraryViewModel;
    }

    // TODO: Interactor reset the Room in DAO, this resets the blackjackViewModel, using the INIT STAGE.
    // TODO: ↑ Done in GameStartPresenter
    @Override
    public void switchToGameLibraryView() {
        viewManagerModel.setState(gameLibraryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
