package use_case.game_start;

import data_access.BlackjackRoomDataAccessObject;
import entity.Card;
import entity.Deck;
import entity.room.BlackjackRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.ApiDataAccessInterface;
import use_case.BlackjackRoomDataAccessInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameStartInteractorTest {

    @Test
    void successTest() {
        // Create DAOs necessary for testing
        BlackjackRoomDataAccessInterface blackjackRoomDAO = new BlackjackRoomDataAccessObject();
        ApiDataAccessInterface apiDataAccesObject = new ApiDataAccessInterface() {
            @Override
            public Deck createDeck() {
                return new Deck("mock_deck_id", 52);
            }

            @Override
            public Deck createDeck(int numDecks) {
                return new Deck("mock_deck_id", 52 * numDecks);
            }

            @Override
            public List<Card> draw(Deck deck, int numCards) {
                return List.of();
            }

            @Override
            public void shuffle(Deck deck) {

            }
        };

        String selectedGame = "blackjack";
        Map<String, Integer> config = new HashMap<>();
        config.put("bankroll", 1000);
        config.put("min_bet", 500);

        GameStartInputData gameStartInputData = new GameStartInputData(selectedGame, config);
        GameStartOutputBoundary gameStartPresenter = new GameStartOutputBoundary() {
            @Override
            public void prepareSuccessView(GameStartOutputData gameStartOutputData) {
                assertEquals("blackjack", gameStartOutputData.getSelectedGame());
                assertEquals(config.get("bankroll"), gameStartOutputData.getPlayerBankroll());

                BlackjackRoom room = blackjackRoomDAO.getRoom();
                assertNotNull(room);

                assertEquals(config.get("bankroll"), room.getPlayerBankroll(BlackjackRoom.HUMAN_PLAYER));
                assertEquals(config.get("min_bet"), room.getMinimumBet());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case fail is not expected.");
            }
        };

        GameStartInputBoundary gameStartInteractor = new GameStartInteractor(gameStartPresenter,
                blackjackRoomDAO, apiDataAccesObject);
        gameStartInteractor.execute(gameStartInputData);
    }

    @Test
    void failureTest() {
        // Create DAOs necessary for testing
        BlackjackRoomDataAccessInterface blackjackRoomDAO = new BlackjackRoomDataAccessObject();
        ApiDataAccessInterface apiDataAccesObject = new ApiDataAccessInterface() {
            @Override
            public Deck createDeck() {
                return new Deck("mock_deck_id", 52);
            }

            @Override
            public Deck createDeck(int numDecks) {
                return new Deck("mock_deck_id", 52 * numDecks);
            }

            @Override
            public List<Card> draw(Deck deck, int numCards) {
                return List.of();
            }

            @Override
            public void shuffle(Deck deck) {

            }
        };

        String selectedGame = "super_fun_game";
        Map<String, Integer> config = new HashMap<>();
        config.put("bankroll", 1000);
        config.put("min_bet", 500);

        GameStartInputData gameStartInputData = new GameStartInputData(selectedGame, config);
        GameStartOutputBoundary gameStartPresenter = new GameStartOutputBoundary() {
            @Override
            public void prepareSuccessView(GameStartOutputData gameStartOutputData) {
                fail("Use case success is not expected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("super_fun_game doesn't exist in program", errorMessage);
            }
        };

        GameStartInputBoundary gameStartInteractor = new GameStartInteractor(gameStartPresenter,
                blackjackRoomDAO, apiDataAccesObject);
        gameStartInteractor.execute(gameStartInputData);
    }
  
}