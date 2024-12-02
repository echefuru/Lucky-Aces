package use_case.game_set_config;

import org.json.JSONObject;

/**
 * Input boundary for the Set Config Use Case.
 */
public interface GameSetConfigInputBoundary {

    /**
     * Execute the Set Config Use Case.
     * @param gameSetConfigInputData the input data of the use case.
     */
    void execute(GameSetConfigInputData gameSetConfigInputData);

    /**
     * Execute the Show Config Panel Use Case.
     * @param config the current config of the game.
     */
    void showConfigPanel(JSONObject config);
}
