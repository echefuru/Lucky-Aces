package interface_adapter.blackjack;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Blackjack View.
 */
public class BlackjackViewModel extends ViewModel<BlackjackState> {

    public BlackjackViewModel() {
        super("blackjack_view");
        setState(new BlackjackState());
    }
}
