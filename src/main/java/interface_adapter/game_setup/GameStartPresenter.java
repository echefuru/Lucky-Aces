package interface_adapter.game_setup;

import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.BlackjackState;
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
        final int playerBankroll = gameStartOutputData.getPlayerBankroll();

        if ("blackjack".equals(selectedGame)) {
            // Start the Blackjack view in a fresh state
            final BlackjackState blackjackState = new BlackjackState();
            blackjackState.setPlayerBankroll(playerBankroll);
            blackjackViewModel.setState(blackjackState);
            blackjackViewModel.firePropertyChanged();

            viewManagerModel.setState(blackjackViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // It shouldn't be possible for this use case to fail during normal usage of the program
        System.out.println(errorMessage);
    }
}
