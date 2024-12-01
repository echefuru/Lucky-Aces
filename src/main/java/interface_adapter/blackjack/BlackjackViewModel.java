package interface_adapter.blackjack;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Blackjack View.
 */
public class BlackjackViewModel extends ViewModel<BlackjackState> {

    private static final String VIEW_NAME = "blackjack_view";

    public BlackjackViewModel() {
        super(VIEW_NAME);
        setState(new BlackjackState());
    }
}
