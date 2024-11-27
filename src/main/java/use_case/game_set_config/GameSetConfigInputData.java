package use_case.game_set_config;

import org.json.JSONObject;

/**
 * The input data for the Set Config Use Case.
 */
public class GameSetConfigInputData {
    private final JSONObject config;

    public GameSetConfigInputData(JSONObject config) {
        this.config = config;
    }

    public JSONObject getConfig() {
        return this.config;
    }
}
