package interface_adapter.blackjack;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_library_select.GameLibraryViewModel;
import use_case.blackjack.exit.ExitOutputBoundary;

/**
 * The Presenter for the Exit Use Case for Blackjack.
 */
public class ExitPresenter implements ExitOutputBoundary {

    private final BlackjackViewModel blackjackViewModel;
    private final GameLibraryViewModel gameLibraryViewModel;
    private final ViewManagerModel viewManagerModel;

    public ExitPresenter(ViewManagerModel viewManagerModel,
                              BlackjackViewModel blackjackViewModel,
                              GameLibraryViewModel gameLibraryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.blackjackViewModel = blackjackViewModel;
        this.gameLibraryViewModel = gameLibraryViewModel;
    }

    // Interactor reset the Room in DAO, this resets the blackjackViewModel, using the INIT STAGE.
    @Override
    public void switchToGameLibraryView() {
        final BlackjackState state = blackjackViewModel.getState();
        state.reset();
        state.setStage("init");
        this.blackjackViewModel.setState(state);
        viewManagerModel.setState(gameLibraryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
