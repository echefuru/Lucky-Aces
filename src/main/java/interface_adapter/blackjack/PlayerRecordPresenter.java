package interface_adapter.blackjack;

import use_case.blackjack.player_record.PlayerRecordOutputBoundary;
import use_case.blackjack.player_record.PlayerRecordOutputData;

/**
 * The Presenter for the Player Record Use Case.
 */
public class PlayerRecordPresenter implements PlayerRecordOutputBoundary {

    private final BlackjackViewModel blackjackViewModel;

    public PlayerRecordPresenter(BlackjackViewModel blackjackViewModel) {
        this.blackjackViewModel = blackjackViewModel;
    }

    @Override
    public void preparePlayerRecordView(PlayerRecordOutputData playerRecordOutputData) {
        final BlackjackState state = blackjackViewModel.getState();
        state.setPlayerHandValRecord(playerRecordOutputData.getHandValueRecord());
        state.setTotalRounds(playerRecordOutputData.getTotalRounds());
        state.setWins(playerRecordOutputData.getTotalWins());
        this.blackjackViewModel.setState(state);
        blackjackViewModel.firePropertyChanged();
    }
}
