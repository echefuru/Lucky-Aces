package interface_adapter.blackjack;

import use_case.blackjack.BlackjackInputBoundary;

/**
 * Controller for the Blackjack Use Cases.
 */
public class BlackjackController {

    private final BlackjackInputBoundary blackjackInteractor;

    public BlackjackController(BlackjackInputBoundary blackjackInteractor) {
        this.blackjackInteractor = blackjackInteractor;
    }

    /**
     * Executes the Exit Use Case which sends viewManager back to GameLibraryView.
     */
    public void switchToGameLibraryView() {
        blackjackInteractor.switchToGameLibraryView();
    }
}
