package use_case.game_set_config;

import java.util.Map;

import org.json.JSONObject;

/**
 * The interactor for the Set Config use case.
 */
public class GameSetConfigInteractor implements GameSetConfigInputBoundary {
    private final GameSetConfigOutputBoundary gameSetConfigPresenter;

    public GameSetConfigInteractor(GameSetConfigOutputBoundary gameSetConfigPresenter) {
        this.gameSetConfigPresenter = gameSetConfigPresenter;
    }

    @Override
    public void execute(GameSetConfigInputData gameSetConfigInputData) {
        final JSONObject config = gameSetConfigInputData.getConfig();
        final Map<String, Integer> newConfigValues = gameSetConfigInputData.getNewConfigValues();
        boolean success = true;
        for (String param : config.keySet()) {
            final JSONObject paramConfig = config.getJSONObject(param);
            final int currValue = newConfigValues.get(param);
            final int maxValue = paramConfig.getInt("max_value");
            final int minValue = paramConfig.getInt("min_value");

            if (currValue > maxValue || currValue < minValue) {
                gameSetConfigPresenter.prepareFailView(paramConfig.getString("name")
                        + " should be between " + minValue + " and " + maxValue + "!");
                success = false;
                break;
            }

            paramConfig.put("curr_value", currValue);
        }

        if (success) {
            final GameSetConfigOutputData gameSetConfigOutputData = new GameSetConfigOutputData(config);
            gameSetConfigPresenter.prepareSuccessView(gameSetConfigOutputData);
        }
    }

    @Override
    public void showConfigPanel(JSONObject config) {
        final GameSetConfigOutputData gameSetConfigOutputData = new GameSetConfigOutputData(config);

        if (!config.isEmpty()) {
            gameSetConfigPresenter.showConfigView(gameSetConfigOutputData);
        }
        else {
            gameSetConfigPresenter.prepareFailView("This game has no config options available.");
        }
    }
}
