package use_case.blackjack.play;

import use_case.blackjack.BlackjackOutputData;

/**
 * The output boundary for the Play Use Case in Blackjack.
 */
public interface PlayOutputBoundary {

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

}
