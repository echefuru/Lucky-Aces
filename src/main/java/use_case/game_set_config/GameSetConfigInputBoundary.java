package use_case.game_set_config;

/**
 * Input boundary for the Set Config Use Case.
 */
public interface GameSetConfigInputBoundary {

    /**
     * Execute the Set Config Use Case.
     * @param gameSetConfigInputData the input data of the use case.
     */
    void execute(GameSetConfigInputData gameSetConfigInputData);

}
