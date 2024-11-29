package use_case.blackjack.again;

/**
 * The output boundary for the Again Use Case in Blackjack.
 */
public interface AgainOutputBoundary {

    /**
     * Updates the BlackjackViewModel to reflect the Again stage of a round.
     */
    void execute();
}
