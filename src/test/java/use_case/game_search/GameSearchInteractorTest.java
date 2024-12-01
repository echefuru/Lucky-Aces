package use_case.game_search;

import data_access.GameInfoDataAccessObject;
import org.junit.Test;
import use_case.GameInfoDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

public class GameSearchInteractorTest {

    @Test
    public void blackjackTest() {
        String searchText = "blackjack";
        GameSearchInputData inputData = new GameSearchInputData(searchText);
        GameInfoDataAccessInterface gameInfoDAO = new GameInfoDataAccessObject("test.json");

        GameSearchOutputBoundary gameSearchPresenter = new GameSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(GameSearchOutputData outputData) {
                boolean[] gameVisible = outputData.getGameVisible();
                assertEquals(4, gameVisible.length);
                assertTrue(gameVisible[0]);
                assertFalse(gameVisible[1]);
                assertFalse(gameVisible[2]);
                assertFalse(gameVisible[3]);
                assertEquals(outputData.getSearchText(), searchText);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        GameSearchInputBoundary interactor = new GameSearchInteractor(gameSearchPresenter, gameInfoDAO);
        interactor.execute(inputData);
    }

    @Test
    public void testTest() {
        String searchText = "test";
        GameSearchInputData inputData = new GameSearchInputData(searchText);
        GameInfoDataAccessInterface gameInfoDAO = new GameInfoDataAccessObject("test.json");

        GameSearchOutputBoundary gameSearchPresenter = new GameSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(GameSearchOutputData outputData) {
                boolean[] gameVisible = outputData.getGameVisible();
                assertEquals(4, gameVisible.length);
                assertFalse(gameVisible[0]);
                assertFalse(gameVisible[1]);
                assertTrue(gameVisible[2]);
                assertTrue(gameVisible[3]);
                assertEquals(outputData.getSearchText(), searchText);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        GameSearchInputBoundary interactor = new GameSearchInteractor(gameSearchPresenter, gameInfoDAO);
        interactor.execute(inputData);
    }

    @Test
    public void upperBlackjackTest() {
        String searchText = "BLACKJACK";
        GameSearchInputData inputData = new GameSearchInputData(searchText);
        GameInfoDataAccessInterface gameInfoDAO = new GameInfoDataAccessObject("test.json");

        GameSearchOutputBoundary gameSearchPresenter = new GameSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(GameSearchOutputData outputData) {
                boolean[] gameVisible = outputData.getGameVisible();
                assertEquals(4, gameVisible.length);
                assertTrue(gameVisible[0]);
                assertFalse(gameVisible[1]);
                assertFalse(gameVisible[2]);
                assertEquals(outputData.getSearchText(), searchText);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        GameSearchInputBoundary interactor = new GameSearchInteractor(gameSearchPresenter, gameInfoDAO);
        interactor.execute(inputData);
    }

    @Test
    public void digitTest() {
        String searchText = "1";
        GameSearchInputData inputData = new GameSearchInputData(searchText);
        GameInfoDataAccessInterface gameInfoDAO = new GameInfoDataAccessObject("test.json");

        GameSearchOutputBoundary gameSearchPresenter = new GameSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(GameSearchOutputData outputData) {
                boolean[] gameVisible = outputData.getGameVisible();
                assertEquals(4, gameVisible.length);
                assertFalse(gameVisible[0]);
                assertFalse(gameVisible[1]);
                assertTrue(gameVisible[2]);
                assertFalse(gameVisible[3]);
                assertEquals(outputData.getSearchText(), searchText);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        GameSearchInputBoundary interactor = new GameSearchInteractor(gameSearchPresenter, gameInfoDAO);
        interactor.execute(inputData);
    }

    @Test
    public void voidTest() {
        String searchText = "";
        GameSearchInputData inputData = new GameSearchInputData(searchText);
        GameInfoDataAccessInterface gameInfoDAO = new GameInfoDataAccessObject("test.json");

        GameSearchOutputBoundary gameSearchPresenter = new GameSearchOutputBoundary() {
            @Override
            public void prepareSuccessView(GameSearchOutputData outputData) {
                boolean[] gameVisible = outputData.getGameVisible();
                assertEquals(4, gameVisible.length);
                assertTrue(gameVisible[0]);
                assertTrue(gameVisible[1]);
                assertTrue(gameVisible[2]);
                assertEquals(outputData.getSearchText(), searchText);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        GameSearchInputBoundary interactor = new GameSearchInteractor(gameSearchPresenter, gameInfoDAO);
        interactor.execute(inputData);
    }
}
