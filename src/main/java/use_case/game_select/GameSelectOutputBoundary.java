package use_case.game_select;

/**
 * The output boundary for the Logged In Use Case.
 */
public interface GameSelectOutputBoundary {

    /**
     * Prepares the success view for the game library Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(GameSelectOutputData outputData);

    /**
     * Prepares the failure view for the game library Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
