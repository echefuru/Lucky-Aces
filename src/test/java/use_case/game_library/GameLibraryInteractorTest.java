package use_case.game_library;

import data_access.GameInfoDataAccessObject;
import org.junit.Test;
import use_case.GameInfoDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

public class GameLibraryInteractorTest {

    @Test
    public void test() {
        GameInfoDataAccessInterface gameInfoDAO = new GameInfoDataAccessObject("test.json");

        GameLibraryOutputBoundary gameLibraryPresenter = new GameLibraryOutputBoundary() {
            @Override
            public void prepareSuccessView(GameLibraryOutputData outputData) {
                String[] gameTypes = outputData.getGameTypes();
                assertEquals(5, gameTypes.length);
                assertEquals("Comparing Card Games", gameTypes[0]);
                assertEquals("Gambling Games", gameTypes[1]);
                assertEquals("Luck-Based Games", gameTypes[2]);
                assertEquals("New Type", gameTypes[3]);
                assertEquals("Strategy-Based Games", gameTypes[4]);

                String[] gamesNames = outputData.getGameNames();
                assertEquals(4, gamesNames.length);
                assertEquals("Blackjack", gamesNames[0]);
                assertEquals("Placeholder Game", gamesNames[1]);
                assertEquals("Test 1", gamesNames[2]);
                assertEquals("Test 2", gamesNames[3]);

                String[] games = outputData.getGames();
                assertEquals(4, games.length);
                assertEquals("blackjack", games[0]);
                assertEquals("placeholdergame", games[1]);
                assertEquals("test1", games[2]);
                assertEquals("test2", games[3]);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        GameLibraryInputBoundary interactor = new GameLibraryInteractor(gameInfoDAO, gameLibraryPresenter);
        interactor.execute();
    }
}
