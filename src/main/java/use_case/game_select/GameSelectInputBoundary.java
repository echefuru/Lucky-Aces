package use_case.game_select;

/**
 * The select game use case.
 */
public interface GameSelectInputBoundary {

    /**
     * Execute the select game use case.
     * @param gameSelectInputData the input data for this use case
     */
    void execute(GameSelectInputData gameSelectInputData);
}
