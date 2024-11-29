package use_case.blackjack.hit;

import use_case.blackjack.BlackjackOutputData;

/**
 * The output boundary for the Hit Use Case in Blackjack.
 */
public interface HitOutputBoundary {

    /**
     * Updates the BlackjackViewModel to reflect the Play stage of a round.
     * @param blackjackOutputData is the output data for the use case passed from Interactor.
     */
    void preparePlayView(BlackjackOutputData blackjackOutputData);

    /**
     * Updates the BlackjackViewModel to reflect the 21 stage of a round.
     * @param blackjackOutputData is the output data for the use case passed from Interactor.
     */
    void prepare21View(BlackjackOutputData blackjackOutputData);

    /**
     * Updates the BlackjackViewModel to reflect the Bust stage of a round.
     * @param blackjackOutputData is the output data for the use case passed from Interactor.
     */
    void prepareBustView(BlackjackOutputData blackjackOutputData);
}
