package use_case.game_search;

/**
 * The output boundary for the Game Search Use Case.
 */
public interface GameSearchOutputBoundary {

    /**
     * Prepares the success view for the game search Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(GameSearchOutputData outputData);

    /**
     * Prepares the failure view for the game search Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
