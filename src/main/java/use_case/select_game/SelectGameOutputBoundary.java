package use_case.select_game;

/**
 * Output boundary for game selection.
 */
public interface SelectGameOutputBoundary {
    /**
     * Prepares the success view for the select game use case.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view for the select game use case.
     */
    void prepareFailView();
}
