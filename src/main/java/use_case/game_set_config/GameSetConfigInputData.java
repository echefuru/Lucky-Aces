package use_case.game_set_config;

import java.util.Map;

import org.json.JSONObject;

/**
 * The input data for the Set Config Use Case.
 */
public class GameSetConfigInputData {
    private final JSONObject config;
    private final Map<String, Integer> newConfigValues;

    public GameSetConfigInputData(JSONObject config, Map<String, Integer> newConfigValues) {
        this.config = config;
        this.newConfigValues = newConfigValues;
    }

    public JSONObject getConfig() {
        return this.config;
    }

    public Map<String, Integer> getNewConfigValues() {
        return this.newConfigValues;
    }
}
