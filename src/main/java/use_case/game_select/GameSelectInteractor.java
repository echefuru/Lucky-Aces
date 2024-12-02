package use_case.game_select;

import org.json.JSONObject;

import use_case.GameInfoDataAccessInterface;

/**
 * The select game interactor.
 */
public class GameSelectInteractor implements GameSelectInputBoundary {

    private final GameSelectOutputBoundary gameSelectPresenter;
    private final GameInfoDataAccessInterface gameInfoDataAccessObject;

    public GameSelectInteractor(GameSelectOutputBoundary gameSelectPresenter,
                                GameInfoDataAccessInterface gameInfoDataAccessObject) {
        this.gameSelectPresenter = gameSelectPresenter;
        this.gameInfoDataAccessObject = gameInfoDataAccessObject;
    }

    @Override
    public void execute(GameSelectInputData gameSelectInputData) {
        final String selectedGame = gameSelectInputData.getSelectedGame();

        if (gameInfoDataAccessObject.isAvailable(selectedGame)) {
            final String gameName = gameInfoDataAccessObject.getName(selectedGame);
            final String gameDescription = gameInfoDataAccessObject.getDescription(selectedGame);
            final String gameRules = gameInfoDataAccessObject.getRules(selectedGame);
            final JSONObject gameDefaultConfig = gameInfoDataAccessObject.getDefaultConfig(selectedGame);
            final GameSelectOutputData gameSelectOutputData = new GameSelectOutputData(selectedGame,
                    gameName, gameDescription, gameRules, gameDefaultConfig);
            gameSelectPresenter.prepareSuccessView(gameSelectOutputData);
        }
        else {
            gameSelectPresenter.prepareFailView(gameInfoDataAccessObject.getName(selectedGame)
                    + " is not available yet, please stay tuned!");
        }
    }
}
