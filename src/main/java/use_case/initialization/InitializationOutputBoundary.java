package use_case.initialization;

/**
 * The output boundary for the Initialization Use Case.
 */
public interface InitializationOutputBoundary {

    /**
     * Prepares the success view for the Initialization Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(InitializationOutputData outputData);

    /**
     * Prepares the failure view for the Initialization Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

}
