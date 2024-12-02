package use_case.game_set_config;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameSetConfigInteractorTest {

    @Test
    void setConfigSuccessTest() {
        String configString =
                        "     {\n" +
                        "      \"min_bet\": {\n" +
                        "        \"name\": \"Minimum Bet\",\n" +
                        "        \"max_value\": 25,\n" +
                        "        \"min_value\": 5,\n" +
                        "        \"curr_value\": 10,\n" +
                        "        \"interval\": 5\n" +
                        "      },\n" +
                        "      \"bankroll\": {\n" +
                        "        \"name\": \"Starting bankroll\",\n" +
                        "        \"max_value\": 1000,\n" +
                        "        \"min_value\": 100,\n" +
                        "        \"curr_value\": 200,\n" +
                        "        \"interval\": 250\n" +
                        "      }\n" +
                        "    }";
        JSONObject config = new JSONObject(configString);
        Map<String, Integer> newValues = new HashMap<>();
        newValues.put("min_bet", 25);
        newValues.put("bankroll", 300);

        GameSetConfigInputData gameSetConfigInputData = new GameSetConfigInputData(config, newValues);
        GameSetConfigOutputBoundary gameSetConfigPresenter = new GameSetConfigOutputBoundary() {
            @Override
            public void prepareSuccessView(GameSetConfigOutputData gameSetConfigOutputData) {
                final JSONObject config = gameSetConfigOutputData.getConfig();

                for (String param : config.keySet()) {
                    final int curr_value = config.getJSONObject(param).getInt("curr_value");
                    final int max_value = config.getJSONObject(param).getInt("max_value");
                    final int min_value = config.getJSONObject(param).getInt("min_value");

                    // Check that config was successfully updated
                    assertEquals(newValues.get(param), curr_value);
                    // Check that the curr_value does fall into correct range
                    assertTrue(min_value <= curr_value && curr_value <= max_value);
                }
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void showConfigView(GameSetConfigOutputData gameSetConfigOutputData) {
                fail("Use case showConfigView is unexpected.");
            }
        };

        GameSetConfigInputBoundary gameSetConfigInteractor = new GameSetConfigInteractor(gameSetConfigPresenter);
        gameSetConfigInteractor.execute(gameSetConfigInputData);
    }

    @Test
    void setConfigTooLargeTest() {
        String configString =
                        "     {\n" +
                        "      \"bankroll\": {\n" +
                        "        \"name\": \"Starting bankroll\",\n" +
                        "        \"max_value\": 1000,\n" +
                        "        \"min_value\": 100,\n" +
                        "        \"curr_value\": 200,\n" +
                        "        \"interval\": 250\n" +
                        "      }\n" +
                        "    }";
        JSONObject config = new JSONObject(configString);
        Map<String, Integer> newValues = new HashMap<>();
        newValues.put("bankroll", 1500);

        GameSetConfigInputData gameSetConfigInputData = new GameSetConfigInputData(config, newValues);
        GameSetConfigOutputBoundary gameSetConfigPresenter = new GameSetConfigOutputBoundary() {
            @Override
            public void prepareSuccessView(GameSetConfigOutputData gameSetConfigOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Starting bankroll should be between 100 and 1000!", errorMessage);
            }

            @Override
            public void showConfigView(GameSetConfigOutputData gameSetConfigOutputData) {
                fail("Use case showConfigView is unexpected.");
            }
        };

        GameSetConfigInputBoundary gameSetConfigInteractor = new GameSetConfigInteractor(gameSetConfigPresenter);
        gameSetConfigInteractor.execute(gameSetConfigInputData);
    }

    @Test
    void setConfigTooSmallTest() {
        String configString =
                        "     {\n" +
                        "      \"bankroll\": {\n" +
                        "        \"name\": \"Starting bankroll\",\n" +
                        "        \"max_value\": 1000,\n" +
                        "        \"min_value\": 100,\n" +
                        "        \"curr_value\": 200,\n" +
                        "        \"interval\": 250\n" +
                        "      }\n" +
                        "    }";
        JSONObject config = new JSONObject(configString);
        Map<String, Integer> newValues = new HashMap<>();
        newValues.put("bankroll", 99);

        GameSetConfigInputData gameSetConfigInputData = new GameSetConfigInputData(config, newValues);
        GameSetConfigOutputBoundary gameSetConfigPresenter = new GameSetConfigOutputBoundary() {
            @Override
            public void prepareSuccessView(GameSetConfigOutputData gameSetConfigOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Starting bankroll should be between 100 and 1000!", errorMessage);
            }

            @Override
            public void showConfigView(GameSetConfigOutputData gameSetConfigOutputData) {
                fail("Use case showConfigView is unexpected.");
            }
        };

        GameSetConfigInputBoundary gameSetConfigInteractor = new GameSetConfigInteractor(gameSetConfigPresenter);
        gameSetConfigInteractor.execute(gameSetConfigInputData);
    }

    @Test
    void emptyShowConfigPanelTest() {
        String configString = "{}";
        JSONObject config = new JSONObject(configString);

        GameSetConfigOutputBoundary gameSetConfigPresenter = new GameSetConfigOutputBoundary() {
            @Override
            public void prepareSuccessView(GameSetConfigOutputData gameSetConfigOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("This game has no config options available.", errorMessage);
            }

            @Override
            public void showConfigView(GameSetConfigOutputData gameSetConfigOutputData) {
                fail("Use case showConfigView is unexpected.");
            }
        };

        GameSetConfigInputBoundary gameSetConfigInteractor = new GameSetConfigInteractor(gameSetConfigPresenter);
        gameSetConfigInteractor.showConfigPanel(config);
    }

    @Test
    void showConfigPanelTest() {
        String configString =
                        "     {\n" +
                        "      \"min_bet\": {\n" +
                        "        \"name\": \"Minimum Bet\",\n" +
                        "        \"max_value\": 25,\n" +
                        "        \"min_value\": 5,\n" +
                        "        \"curr_value\": 10,\n" +
                        "        \"interval\": 5\n" +
                        "      },\n" +
                        "      \"bankroll\": {\n" +
                        "        \"name\": \"Starting bankroll\",\n" +
                        "        \"max_value\": 1000,\n" +
                        "        \"min_value\": 100,\n" +
                        "        \"curr_value\": 200,\n" +
                        "        \"interval\": 250\n" +
                        "      }\n" +
                        "    }";
        JSONObject config = new JSONObject(configString);

        GameSetConfigOutputBoundary gameSetConfigPresenter = new GameSetConfigOutputBoundary() {

            @Override
            public void prepareSuccessView(GameSetConfigOutputData gameSetConfigOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case fail is unexpected.");
            }

            @Override
            public void showConfigView(GameSetConfigOutputData gameSetConfigOutputData) {

            }
        };

        GameSetConfigInputBoundary gameSetConfigInteractor = new GameSetConfigInteractor(gameSetConfigPresenter);
        gameSetConfigInteractor.showConfigPanel(config);
    }
}