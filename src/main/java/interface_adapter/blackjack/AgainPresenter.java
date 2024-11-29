package interface_adapter.blackjack;

import use_case.blackjack.again.AgainOutputBoundary;

/**
 * The Presenter for the Again Use Case in Blackjack.
 */
public class AgainPresenter implements AgainOutputBoundary {

    private final BlackjackViewModel blackjackViewModel;

    public AgainPresenter(BlackjackViewModel blackjackViewModel) {
        this.blackjackViewModel = blackjackViewModel;
    }

    @Override
    public void execute() {
        final BlackjackState state = blackjackViewModel.getState();
        state.setPlayerCards(null);
        state.setPlayerTotal(0);
        state.setDealerCards(null);
        state.setStage("again");
        this.blackjackViewModel.setState(state);
        blackjackViewModel.firePropertyChanged();
    }
}
