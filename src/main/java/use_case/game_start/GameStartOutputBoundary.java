package use_case.game_start;

/**
 * Output boundary for game setup.
 */
public interface GameStartOutputBoundary {
    /**
     * Prepares the success view for the select game use case.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view for the select game use case.
     */
    void prepareFailView();
}
