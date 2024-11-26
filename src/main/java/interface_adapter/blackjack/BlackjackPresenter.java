package interface_adapter.blackjack;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_library_select.GameLibraryViewModel;
import use_case.blackjack.BlackjackOutputBoundary;

public class BlackjackPresenter implements BlackjackOutputBoundary {

    private final BlackjackViewModel blackjackViewModel;
    private final GameLibraryViewModel gameLibraryViewModel;
    private final ViewManagerModel viewManagerModel;

    public BlackjackPresenter(ViewManagerModel viewManagerModel,
                              BlackjackViewModel blackjackViewModel,
                              GameLibraryViewModel gameLibraryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.blackjackViewModel = blackjackViewModel;
        this.gameLibraryViewModel = gameLibraryViewModel;
    }

    @Override
    public void switchToGameLibraryView() {
        viewManagerModel.setState(gameLibraryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
