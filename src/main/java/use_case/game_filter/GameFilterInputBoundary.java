package use_case.game_filter;

/**
 * The Game Filter Use Case.
 */
public interface GameFilterInputBoundary {

    /**
     * Execute the game filter Use Case and process the input data.
     * @param gameFilterInputData the input data for this use case
     */
    void execute(GameFilterInputData gameFilterInputData);
}
