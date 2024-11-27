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
     * Executes the Play Use Case.
     */
    public void play() {
        blackjackInteractor.play();
    }

    /**
     * Executes the Hit Use Case.
     */
    public void hit() {
        blackjackInteractor.hit();
    }

    /**
     * Executes the Hold Use Case.
     */
    public void hold() {
        blackjackInteractor.hold();
    }

    /**
     * Executes the Exit Use Case which sends viewManager back to GameLibraryView.
     */
    public void switchToGameLibraryView() {
        blackjackInteractor.switchToGameLibraryView();
    }
}
