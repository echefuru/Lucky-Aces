package interface_adapter.game_setup;

import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.BlackjackViewModel;
import use_case.game_start.GameStartOutputBoundary;
import use_case.game_start.GameStartOutputData;

/**
 * Presenter for the game setup use case.
 */
public class GameStartPresenter implements GameStartOutputBoundary {
    private ViewManagerModel viewManagerModel;

    private BlackjackViewModel blackjackViewModel;

    public GameStartPresenter(ViewManagerModel viewManagerModel, BlackjackViewModel blackjackViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.blackjackViewModel = blackjackViewModel;
    }

    @Override
    public void prepareSuccessView(GameStartOutputData gameStartOutputData) {
        final String selectedGame = gameStartOutputData.getSelectedGame();

        if ("blackjack".equals(selectedGame)) {
            // TODO: Finish link to the Blackjack View
            viewManagerModel.setState(blackjackViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // It shouldn't be possible for this use case to fail
        System.out.println(errorMessage);
    }
}
