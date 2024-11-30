package use_case.game_library;

import data_access.GameInfoDataAccessObject;
import org.junit.Test;
import use_case.GameInfoDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

public class GameLibraryInteractorTest {

    @Test
    public void test() {
        GameInfoDataAccessInterface gameInfoDAO = new GameInfoDataAccessObject("game_info.json");

        GameLibraryOutputBoundary gameLibraryPresenter = new GameLibraryOutputBoundary() {
            @Override
            public void prepareSuccessView(GameLibraryOutputData outputData) {
                String[] gameTypes = outputData.getGameTypes();
                assertEquals(4, gameTypes.length);
                assertEquals("Comparing Card Games", gameTypes[0]);
                assertEquals("Gambling Games", gameTypes[1]);
                assertEquals("Luck-Based Games", gameTypes[2]);
                assertEquals("Strategy-Based Games", gameTypes[3]);

                String[] gamesNames = outputData.getGameNames();
                assertEquals(3, gamesNames.length);
                assertEquals("Blackjack", gamesNames[0]);
                assertEquals("Placeholder 1", gamesNames[1]);
                assertEquals("Placeholder 2", gamesNames[2]);

                String[] games = outputData.getGames();
                assertEquals(3, games.length);
                assertEquals("blackjack", games[0]);
                assertEquals("placeholder1", games[1]);
                assertEquals("placeholder2", games[2]);
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
