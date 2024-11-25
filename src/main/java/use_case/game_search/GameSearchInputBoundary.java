package use_case.game_search;

/**
 * The Game Search Use Case.
 */
public interface GameSearchInputBoundary {

    /**
     * Execute the game search Use Case.
     * @param gameSearchInputData the input data for this use case
     */
    void execute(GameSearchInputData gameSearchInputData);
}
