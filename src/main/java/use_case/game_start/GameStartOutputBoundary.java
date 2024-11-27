package use_case.game_start;

/**
 * Output boundary for game setup.
 */
public interface GameStartOutputBoundary {
    /**
     * Prepares the success view for the select game use case.
     * @param gameStartOutputData the output data of the use case
     */
    void prepareSuccessView(GameStartOutputData gameStartOutputData);

    /**
     * Prepares the failure view for the select game use case.
     * @param errorMessage the error message for the failed use case
     */
    void prepareFailView(String errorMessage);
}
