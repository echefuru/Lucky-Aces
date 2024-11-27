package use_case.game_set_config;

import org.json.JSONObject;

/**
 * The output data for the Set Config Use Case.
 */
public class GameSetConfigOutputData {
    private final JSONObject config;

    public GameSetConfigOutputData(JSONObject config) {
        this.config = config;
    }

    public JSONObject getConfig() {
        return config;
    }
}
