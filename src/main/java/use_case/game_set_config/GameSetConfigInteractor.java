package use_case.game_set_config;

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
        boolean success = true;
        for (String param : config.keySet()) {
            final JSONObject paramConfig = config.getJSONObject(param);
            final int currValue = paramConfig.getInt("curr_value");
            final int maxValue = paramConfig.getInt("max_value");
            final int minValue = paramConfig.getInt("min_value");
            if (currValue > maxValue || currValue < minValue) {
                gameSetConfigPresenter.prepareFailView(paramConfig.getString("name")
                        + " should be between " + maxValue + " and " + minValue + "!");
                success = false;
                break;
            }
        }

        if (success) {
            final GameSetConfigOutputData gameSetConfigOutputData = new GameSetConfigOutputData(config);
            gameSetConfigPresenter.prepareSuccessView(gameSetConfigOutputData);
        }
    }
}
