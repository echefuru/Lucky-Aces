package use_case.blackjack.hold;

import use_case.blackjack.BlackjackOutputData;

/**
 * The output boundary for the Hit Use Case in Blackjack.
 */
public interface HoldOutputBoundary {

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
}
