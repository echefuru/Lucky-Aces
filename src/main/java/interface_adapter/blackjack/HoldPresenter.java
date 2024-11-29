package interface_adapter.blackjack;

import use_case.blackjack.BlackjackOutputData;
import use_case.blackjack.hit.HitOutputBoundary;
import use_case.blackjack.hold.HoldOutputBoundary;

/**
 * The Presenter for the Hold Use Case in Blackjack.
 */
public class HoldPresenter implements HoldOutputBoundary {

    private final BlackjackViewModel blackjackViewModel;

    public HoldPresenter(BlackjackViewModel blackjackViewModel) {
        this.blackjackViewModel = blackjackViewModel;
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
}
