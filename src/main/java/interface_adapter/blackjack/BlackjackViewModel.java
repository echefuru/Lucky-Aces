package interface_adapter.blackjack;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Blackjack View.
 */
public class BlackjackViewModel extends ViewModel<BlackjackState> {

    public static final String HIT_LABEL = "HIT!";
    public static final String HOLD_LABEL = "HOLD";
    public static final String DOUBLE_LABEL = "DOUBLE!!";
    public static final String EXIT_LABEL = "EXIT";

    public BlackjackViewModel() {
        super("blackjack_view");
        setState(new BlackjackState());
    }
}
