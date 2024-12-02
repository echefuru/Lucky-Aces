package interface_adapter.game_setup;

import java.util.Map;

import org.json.JSONObject;

import use_case.game_set_config.GameSetConfigInputBoundary;
import use_case.game_set_config.GameSetConfigInputData;

/**
 * The controller fo the Set Config Use Case.
 */
public class GameSetConfigController {
    private final GameSetConfigInputBoundary gameSetConfigInteractor;

    public GameSetConfigController(GameSetConfigInputBoundary gameSetConfigInteractor) {
        this.gameSetConfigInteractor = gameSetConfigInteractor;
    }

    /**
     * Executes the Set Config Use Case.
     * @param config a JSONObject holding the current config of the game.
     * @param newConfigValues a map holding the config set by the user.
     */
    public void execute(JSONObject config, Map<String, Integer> newConfigValues) {
        final GameSetConfigInputData gameSetConfigInputData = new GameSetConfigInputData(config, newConfigValues);

        gameSetConfigInteractor.execute(gameSetConfigInputData);
    }

    /**
     * Executes the Show Game Config Panel Use Case.
     * @param config a JSONObject holding the current config set by the user.
     */
    public void showConfigPanel(JSONObject config) {
        gameSetConfigInteractor.showConfigPanel(config);
    }
}
