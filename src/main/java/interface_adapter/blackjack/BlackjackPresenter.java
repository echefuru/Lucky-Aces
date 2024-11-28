package interface_adapter.blackjack;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_library_select.GameLibraryViewModel;
import use_case.blackjack.BlackjackOutputBoundary;
import use_case.blackjack.BlackjackOutputData;

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
    public void preparePlayView(BlackjackOutputData blackjackOutputData) {
        final BlackjackState state = blackjackViewModel.getState();
        state.setPlayerCards(blackjackOutputData.getPlayerCards());
        state.setPlayerTotal(blackjackOutputData.getPlayerTotal());
        state.setDealerCards(blackjackOutputData.getDealerCards());
        state.setStage("play");
        this.blackjackViewModel.setState(state);
        blackjackViewModel.firePropertyChanged();
    }

    @Override
    public void prepare21View(BlackjackOutputData blackjackOutputData) {
        final BlackjackState state = blackjackViewModel.getState();
        state.setPlayerCards(blackjackOutputData.getPlayerCards());
        state.setPlayerTotal(blackjackOutputData.getPlayerTotal());
        state.setDealerCards(blackjackOutputData.getDealerCards());
        state.setStage("21");
        this.blackjackViewModel.setState(state);
        blackjackViewModel.firePropertyChanged();
    }

    @Override
    public void prepareWinView(BlackjackOutputData blackjackOutputData) {
        final BlackjackState state = blackjackViewModel.getState();
        state.setPlayerCards(blackjackOutputData.getPlayerCards());
        state.setDealerCards(blackjackOutputData.getDealerCards());
        state.incrementWins();
        state.setStage("win");
        this.blackjackViewModel.setState(state);
        blackjackViewModel.firePropertyChanged();
    }

    @Override
    public void prepareDrawView(BlackjackOutputData blackjackOutputData) {
        final BlackjackState state = blackjackViewModel.getState();
        state.setPlayerCards(blackjackOutputData.getPlayerCards());
        state.setDealerCards(blackjackOutputData.getDealerCards());
        state.setStage("draw");
        this.blackjackViewModel.setState(state);
        blackjackViewModel.firePropertyChanged();
    }

    @Override
    public void prepareLossView(BlackjackOutputData blackjackOutputData) {
        final BlackjackState state = blackjackViewModel.getState();
        state.setPlayerCards(blackjackOutputData.getPlayerCards());
        state.setDealerCards(blackjackOutputData.getDealerCards());
        state.incrementLosses();
        state.setStage("loss");
        this.blackjackViewModel.setState(state);
        blackjackViewModel.firePropertyChanged();
    }

    @Override
    public void prepareBustView(BlackjackOutputData blackjackOutputData) {
        final BlackjackState state = blackjackViewModel.getState();
        state.setPlayerCards(blackjackOutputData.getPlayerCards());
        state.setDealerCards(blackjackOutputData.getDealerCards());
        state.incrementLosses();
        state.setStage("bust");
        this.blackjackViewModel.setState(state);
        blackjackViewModel.firePropertyChanged();
    }

    @Override
    public void preparePlayAgainView() {
        final BlackjackState state = blackjackViewModel.getState();
        state.setPlayerCards(null);
        state.setPlayerTotal(0);
        state.setDealerCards(null);
        state.setStage("play-again");
        this.blackjackViewModel.setState(state);
        blackjackViewModel.firePropertyChanged();
    }

    // TODO: Interactor reset the Room in DAO, this resets the blackjackViewModel, we need an INIT STAGE.
    @Override
    public void switchToGameLibraryView() {
        viewManagerModel.setState(gameLibraryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
