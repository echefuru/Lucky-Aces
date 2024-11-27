package interface_adapter.game_view;

import interface_adapter.ViewModel;

/**
 * View Model for Blackjack Use Case.
 */
public class BlackjackViewModel extends ViewModel<BlackjackState> {
    private static final String VIEW_NAME = "blackjack";

    public BlackjackViewModel() {
        super(VIEW_NAME);
        setState(new BlackjackState());
    }
}
