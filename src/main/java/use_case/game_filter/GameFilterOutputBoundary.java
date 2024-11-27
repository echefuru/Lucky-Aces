package use_case.game_filter;

/**
 * The output boundary for the Game Filter Use Case.
 */
public interface GameFilterOutputBoundary {

    /**
     * Prepares the success view for the game filter Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(GameFilterOutputData outputData);

    /**
     * Prepares the failure view for the game filter Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
