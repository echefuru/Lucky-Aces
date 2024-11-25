package use_case.game_library;

/**
 * The output boundary for the Initialization Use Case.
 */
public interface GameLibraryOutputBoundary {

    /**
     * Prepares the success view for the Initialization Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(GameLibraryOutputData outputData);

    /**
     * Prepares the failure view for the Initialization Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
