package interface_adapter.blackjack;

import use_case.blackjack.BlackjackOutputData;
import use_case.blackjack.play.PlayOutputBoundary;

/**
 * The Presenter for the Play Use Case in Blackjack.
 */
public class PlayPresenter implements PlayOutputBoundary {

    private final BlackjackViewModel blackjackViewModel;

    public PlayPresenter(BlackjackViewModel blackjackViewModel) {
        this.blackjackViewModel = blackjackViewModel;
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
}
