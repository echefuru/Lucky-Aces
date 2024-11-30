package use_case.game_filter;

import data_access.GameInfoDataAccessObject;
import org.junit.Test;
import use_case.GameInfoDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

public class GameFilterInteractorTest {

    @Test
    public void luckBased_3test() {
        String gametype = "Luck-Based Games";
        int playerCount = 3;
        GameFilterInputData inputData = new GameFilterInputData(gametype, playerCount);
        GameInfoDataAccessInterface gameInfoDAO = new GameInfoDataAccessObject("game_info.json");

        GameFilterOutputBoundary gameFilterPresenter = new GameFilterOutputBoundary() {
            @Override
            public void prepareSuccessView(GameFilterOutputData outputData) {
                boolean[] gameVisible = outputData.getGamesVisible();
                assertTrue(gameVisible[0]);
                assertFalse(gameVisible[1]);
                assertFalse(gameVisible[2]);
                assertEquals(outputData.getGamesType(), gametype);
                assertEquals(outputData.getPlayerCount(), playerCount);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        GameFilterInputBoundary interactor = new GameFilterInteractor(gameFilterPresenter, gameInfoDAO);
        interactor.execute(inputData);
    }

    @Test
    public void anytest() {
        String gametype = "";
        int playerCount = -1;
        GameFilterInputData inputData = new GameFilterInputData(gametype, playerCount);
        GameInfoDataAccessInterface gameInfoDAO = new GameInfoDataAccessObject("game_info.json");

        GameFilterOutputBoundary gameFilterPresenter = new GameFilterOutputBoundary() {
            @Override
            public void prepareSuccessView(GameFilterOutputData outputData) {
                boolean[] gameVisible = outputData.getGamesVisible();
                assertTrue(gameVisible[0]);
                assertTrue(gameVisible[1]);
                assertTrue(gameVisible[2]);
                assertEquals(outputData.getGamesType(), gametype);
                assertEquals(outputData.getPlayerCount(), playerCount);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        GameFilterInputBoundary interactor = new GameFilterInteractor(gameFilterPresenter, gameInfoDAO);
        interactor.execute(inputData);
    }

    @Test
    public void strategy_0test() {
        String gametype = "Strategy-Based Games";
        int playerCount = 0;
        GameFilterInputData inputData = new GameFilterInputData(gametype, playerCount);
        GameInfoDataAccessInterface gameInfoDAO = new GameInfoDataAccessObject("game_info.json");

        GameFilterOutputBoundary gameFilterPresenter = new GameFilterOutputBoundary() {
            @Override
            public void prepareSuccessView(GameFilterOutputData outputData) {
                boolean[] gameVisible = outputData.getGamesVisible();
                assertFalse(gameVisible[0]);
                assertTrue(gameVisible[1]);
                assertFalse(gameVisible[2]);
                assertEquals(outputData.getGamesType(), gametype);
                assertEquals(outputData.getPlayerCount(), playerCount);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        GameFilterInputBoundary interactor = new GameFilterInteractor(gameFilterPresenter, gameInfoDAO);
        interactor.execute(inputData);
    }

    @Test
    public void luck_100test() {
        String gametype = "Luck-Based Games";
        int playerCount = 100;
        GameFilterInputData inputData = new GameFilterInputData(gametype, playerCount);
        GameInfoDataAccessInterface gameInfoDAO = new GameInfoDataAccessObject("game_info.json");

        GameFilterOutputBoundary gameFilterPresenter = new GameFilterOutputBoundary() {
            @Override
            public void prepareSuccessView(GameFilterOutputData outputData) {
                boolean[] gameVisible = outputData.getGamesVisible();
                assertFalse(gameVisible[0]);
                assertFalse(gameVisible[1]);
                assertFalse(gameVisible[2]);
                assertEquals(outputData.getGamesType(), gametype);
                assertEquals(outputData.getPlayerCount(), playerCount);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        GameFilterInputBoundary interactor = new GameFilterInteractor(gameFilterPresenter, gameInfoDAO);
        interactor.execute(inputData);
    }
}
