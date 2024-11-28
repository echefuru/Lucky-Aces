package use_case.blackjack;

/**
 * The output boundary for the Blackjack Use Cases.
 */
public interface BlackjackOutputBoundary {

    /**
     * Updates the BlackjackViewModel to reflect the Play stage of a round.
     * @param blackjackOutputData is the output data for the use case passed from Interactor.
     */
    void preparePlayView(BlackjackOutputData blackjackOutputData);

    /**
     * Updates the BlackjackViewModel to reflect the Win stage of a round.
     * @param blackjackOutputData is the output data for the use case passed from Interactor.
     */
    void prepareWinView(BlackjackOutputData blackjackOutputData);

    /**
     * Updates the BlackjackViewModel to reflect the Draw stage of a round.
     * @param blackjackOutputData is the output data for the use case passed from Interactor.
     */
    void prepareDrawView(BlackjackOutputData blackjackOutputData);

    /**
     * Updates the BlackjackViewModel to reflect the Loss stage of a round.
     * @param blackjackOutputData is the output data for the use case passed from Interactor.
     */
    void prepareLossView(BlackjackOutputData blackjackOutputData);

    /**
     * Updates the BlackjackViewModel to reflect the Bust stage of a round.
     * @param blackjackOutputData is the output data for the use case passed from Interactor.
     */
    void prepareBustView(BlackjackOutputData blackjackOutputData);

    /**
     * Updates the BlackjackViewModel to reflect the Play Again stage of a round.
     */
    void preparePlayAgainView();

    /**
     * Switches to the GameLibraryView.
     */
    void switchToGameLibraryView();
}
