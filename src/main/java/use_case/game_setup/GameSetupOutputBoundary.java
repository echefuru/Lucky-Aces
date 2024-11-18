package use_case.game_setup;

/**
 * Output boundary for game setup.
 */
public interface GameSetupOutputBoundary {
    /**
     * Prepares the success view for the select game use case.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view for the select game use case.
     */
    void prepareFailView();

    /**
     * Switch to the game library view.
     */
    void switchToGameLibraryView();
}
