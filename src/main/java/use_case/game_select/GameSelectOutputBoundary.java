package use_case.game_select;

/**
 * The output boundary for the select game use case.
 */
public interface GameSelectOutputBoundary {

    /**
     * Prepares the success view for the select game use case.
     * @param outputData the output data
     */
    void prepareSuccessView(GameSelectOutputData outputData);

    /**
     * Prepares the failure view for the select game use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
