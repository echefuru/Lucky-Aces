package use_case.game_select;

import data_access.GameInfoDataAccessObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import use_case.GameInfoDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

class GameSelectInteractorTest {

    @Test
    void successTest() {
        GameInfoDataAccessInterface gameInfoDAO = new GameInfoDataAccessObject("test.json");
        GameSelectInputData gameSelectInputData = new GameSelectInputData("blackjack");

        GameSelectOutputBoundary gameSelectPresenter = new GameSelectOutputBoundary() {
            @Override
            public void prepareSuccessView(GameSelectOutputData outputData) {
                assertEquals("blackjack", outputData.getSelectedGame());
                assertEquals("Blackjack description", outputData.getGameDescription());
                assertEquals("Blackjack rules", outputData.getGameRules());
                assertEquals("Blackjack", outputData.getGameName());
                assertEquals("some_value", outputData.getGameDefaultConfig().getString("some_config"));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case fail is not expected.");
            }
        };

        GameSelectInputBoundary gameSelectInteractor = new GameSelectInteractor(gameSelectPresenter, gameInfoDAO);
        gameSelectInteractor.execute(gameSelectInputData);
    }

    @Test
    void failTest() {
        GameInfoDataAccessInterface gameInfoDAO = new GameInfoDataAccessObject("test.json");
        GameSelectInputData gameSelectInputData = new GameSelectInputData("test2");

        GameSelectOutputBoundary gameSelectPresenter = new GameSelectOutputBoundary() {
            @Override
            public void prepareSuccessView(GameSelectOutputData outputData) {
                fail("Use case success is not expected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Test 2 is not available yet, please stay tuned!", errorMessage);
            }
        };

        GameSelectInputBoundary gameSelectInteractor = new GameSelectInteractor(gameSelectPresenter, gameInfoDAO);
        gameSelectInteractor.execute(gameSelectInputData);
    }

}