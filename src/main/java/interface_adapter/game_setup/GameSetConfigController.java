package interface_adapter.game_setup;

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
     * @param config a JSONObject holding the current config set by the user.
     */
    public void execute(JSONObject config) {
        final GameSetConfigInputData gameSetConfigInputData = new GameSetConfigInputData(config);

        gameSetConfigInteractor.execute(gameSetConfigInputData);
    }
}
