package use_case.game_setup;

/**
 * Input boundary for game setup.
 */
public interface GameSetupInputBoundary {
    /**
     * Executes the select game use case.
     */
    void execute();

    /**
     * Switch to the game library view.
     */
    void switchToGameLibraryView();
}
