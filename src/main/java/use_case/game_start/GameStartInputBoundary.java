package use_case.game_start;

/**
 * Input boundary for game setup.
 */
public interface GameStartInputBoundary {
    /**
     * Executes the Game Start Use Case.
     * @param gameStartInputData the input data for this use case
     */
    void execute(GameStartInputData gameStartInputData);
}
