package use_case.blackjack.hold;

import data_access.BlackjackRoomDataAccessObject;
import entity.Card;
import entity.Deck;
import entity.Rank;
import entity.Suit;
import entity.room.BlackjackRoom;
import org.junit.jupiter.api.Test;
import use_case.ApiDataAccessInterface;
import use_case.BlackjackRoomDataAccessInterface;
import use_case.blackjack.BlackjackOutputData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HoldInteractorTest {

    @Test
    void winMoreTest() {
        ApiDataAccessInterface apiMockDao = new ApiDataAccessInterface() {
            @Override
            public Deck createDeck() {
                return new Deck("test_id", 0);
            }

            @Override
            public Deck createDeck(int numDecks) {
                return new Deck("test_id", 0);
            }

            @Override
            public List<Card> draw(Deck deck, int numCards) {
                return new ArrayList<>(Arrays.asList(new Card(Rank.KING, Suit.SPADES, "KS"),
                        new Card(Rank.KING, Suit.HEARTS, "KH"),
                        new Card(Rank.SEVEN, Suit.HEARTS, "7H")));
            }

            @Override
            public void shuffle(Deck deck) {
                return;
            }
        };
        BlackjackRoomDataAccessInterface blackjackRoomDao = new BlackjackRoomDataAccessObject();

        // For the success test, we need to add a BlackjackRoom to the data access object first.
        blackjackRoomDao.createRoom(apiMockDao.createDeck(2), 100, 25);

        blackjackRoomDao.getRoom().getHumanPlayer().setCards(new ArrayList<>(Arrays.asList(
                new Card(Rank.ACE, Suit.SPADES, "AS"),
                new Card(Rank.KING, Suit.HEARTS, "KH"))));

        HoldOutputBoundary presenter = new HoldOutputBoundary() {
            @Override
            public void prepareWinView(BlackjackOutputData outputData) {
                assertEquals(21, blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.HUMAN_PLAYER));
                assertEquals(27, blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.DEALER));
            }

            @Override
            public void prepareDrawView(BlackjackOutputData outputData) {
                fail("This should have gone to Win view.");
            }

            @Override
            public void prepareGameOverView(BlackjackOutputData outputData) {
                fail("This should have gone to Win view.");
            }

            @Override
            public void prepareLossView(BlackjackOutputData outputData) {
                fail("This should have gone to Win view.");
            }
        };

        HoldInputBoundary interactor = new HoldInteractor(apiMockDao, blackjackRoomDao, presenter);
        interactor.execute();
    }

    @Test
    void winLessTest() {
        ApiDataAccessInterface apiMockDao = new ApiDataAccessInterface() {
            @Override
            public Deck createDeck() {
                return new Deck("test_id", 0);
            }

            @Override
            public Deck createDeck(int numDecks) {
                return new Deck("test_id", 0);
            }

            @Override
            public List<Card> draw(Deck deck, int numCards) {
                return new ArrayList<>(Arrays.asList(new Card(Rank.KING, Suit.SPADES, "KS"),
                        new Card(Rank.KING, Suit.HEARTS, "KH")));
            }

            @Override
            public void shuffle(Deck deck) {
                return;
            }
        };
        BlackjackRoomDataAccessInterface blackjackRoomDao = new BlackjackRoomDataAccessObject();

        // For the success test, we need to add a BlackjackRoom to the data access object first.
        blackjackRoomDao.createRoom(apiMockDao.createDeck(2), 100, 25);

        blackjackRoomDao.getRoom().getHumanPlayer().setCards(new ArrayList<>(Arrays.asList(
                new Card(Rank.ACE, Suit.SPADES, "AS"),
                new Card(Rank.KING, Suit.HEARTS, "KH"))));

        HoldOutputBoundary presenter = new HoldOutputBoundary() {
            @Override
            public void prepareWinView(BlackjackOutputData outputData) {
                assertEquals(21, blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.HUMAN_PLAYER));
                assertEquals(20, blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.DEALER));
            }

            @Override
            public void prepareDrawView(BlackjackOutputData outputData) {
                fail("This should have gone to Win view.");
            }

            @Override
            public void prepareGameOverView(BlackjackOutputData outputData) {
                fail("This should have gone to Win view.");
            }

            @Override
            public void prepareLossView(BlackjackOutputData outputData) {
                fail("This should have gone to Win view.");
            }
        };

        HoldInputBoundary interactor = new HoldInteractor(apiMockDao, blackjackRoomDao, presenter);
        interactor.execute();
    }

    @Test
    void drawTest() {
        ApiDataAccessInterface apiMockDao = new ApiDataAccessInterface() {
            @Override
            public Deck createDeck() {
                return new Deck("test_id", 0);
            }

            @Override
            public Deck createDeck(int numDecks) {
                return new Deck("test_id", 0);
            }

            @Override
            public List<Card> draw(Deck deck, int numCards) {
                return new ArrayList<>(Arrays.asList(new Card(Rank.NINE, Suit.SPADES, "9S")));
            }

            @Override
            public void shuffle(Deck deck) {
                return;
            }
        };
        BlackjackRoomDataAccessInterface blackjackRoomDao = new BlackjackRoomDataAccessObject();

        // For the success test, we need to add a BlackjackRoom to the data access object first.
        blackjackRoomDao.createRoom(apiMockDao.createDeck(2), 100, 25);

        blackjackRoomDao.getRoom().getHumanPlayer().setCards(new ArrayList<>(Arrays.asList(
                new Card(Rank.EIGHT, Suit.SPADES, "8S"),
                new Card(Rank.KING, Suit.HEARTS, "KH"))));

        HoldOutputBoundary presenter = new HoldOutputBoundary() {
            @Override
            public void prepareWinView(BlackjackOutputData outputData) {
                fail("This should have gone to Draw view.");
            }

            @Override
            public void prepareDrawView(BlackjackOutputData outputData) {
                assertEquals(18, blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.HUMAN_PLAYER));
                assertEquals(18, blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.DEALER));
            }

            @Override
            public void prepareGameOverView(BlackjackOutputData outputData) {
                fail("This should have gone to Draw view.");
            }

            @Override
            public void prepareLossView(BlackjackOutputData outputData) {
                fail("This should have gone to Draw view.");
            }
        };

        HoldInputBoundary interactor = new HoldInteractor(apiMockDao, blackjackRoomDao, presenter);
        interactor.execute();
    }

    @Test
    void gameOverTest() {
        ApiDataAccessInterface apiMockDao = new ApiDataAccessInterface() {
            @Override
            public Deck createDeck() {
                return new Deck("test_id", 0);
            }

            @Override
            public Deck createDeck(int numDecks) {
                return new Deck("test_id", 0);
            }

            @Override
            public List<Card> draw(Deck deck, int numCards) {
                return new ArrayList<>(Arrays.asList(new Card(Rank.KING, Suit.SPADES, "KS"),
                        new Card(Rank.ACE, Suit.HEARTS, "AH")));
            }

            @Override
            public void shuffle(Deck deck) {
                return;
            }
        };
        BlackjackRoomDataAccessInterface blackjackRoomDao = new BlackjackRoomDataAccessObject();

        // For the success test, we need to add a BlackjackRoom to the data access object first.
        blackjackRoomDao.createRoom(apiMockDao.createDeck(2), 0, 25);

        blackjackRoomDao.getRoom().getHumanPlayer().setCards(new ArrayList<>(Arrays.asList(
                new Card(Rank.SEVEN, Suit.SPADES, "7S"),
                new Card(Rank.KING, Suit.HEARTS, "KH"))));

        HoldOutputBoundary presenter = new HoldOutputBoundary() {
            @Override
            public void prepareWinView(BlackjackOutputData outputData) {
                fail("This should have gone to Game Over view.");
            }

            @Override
            public void prepareDrawView(BlackjackOutputData outputData) {
                fail("This should have gone to Game Over view.");
            }

            @Override
            public void prepareGameOverView(BlackjackOutputData outputData) {
                assertTrue(blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.HUMAN_PLAYER)
                        < blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.DEALER) &
                        blackjackRoomDao.getRoom().getPlayerBankroll(BlackjackRoom.HUMAN_PLAYER)
                                < blackjackRoomDao.getRoom().getMinimumBet());
            }

            @Override
            public void prepareLossView(BlackjackOutputData outputData) {
                fail("This should have gone to Game Over view.");
            }
        };

        HoldInputBoundary interactor = new HoldInteractor(apiMockDao, blackjackRoomDao, presenter);
        interactor.execute();
    }

    @Test
    void lossTest() {
        ApiDataAccessInterface apiMockDao = new ApiDataAccessInterface() {
            @Override
            public Deck createDeck() {
                return new Deck("test_id", 0);
            }

            @Override
            public Deck createDeck(int numDecks) {
                return new Deck("test_id", 0);
            }

            @Override
            public List<Card> draw(Deck deck, int numCards) {
                return new ArrayList<>(Arrays.asList(new Card(Rank.KING, Suit.SPADES, "KS"),
                        new Card(Rank.ACE, Suit.HEARTS, "AH")));
            }

            @Override
            public void shuffle(Deck deck) {
                return;
            }
        };
        BlackjackRoomDataAccessInterface blackjackRoomDao = new BlackjackRoomDataAccessObject();

        // For the success test, we need to add a BlackjackRoom to the data access object first.
        blackjackRoomDao.createRoom(apiMockDao.createDeck(2), 100, 25);

        blackjackRoomDao.getRoom().getHumanPlayer().setCards(new ArrayList<>(Arrays.asList(
                new Card(Rank.SEVEN, Suit.SPADES, "7S"),
                new Card(Rank.KING, Suit.HEARTS, "KH"))));

        HoldOutputBoundary presenter = new HoldOutputBoundary() {
            @Override
            public void prepareWinView(BlackjackOutputData outputData) {
                fail("This should have gone to Loss view.");
            }

            @Override
            public void prepareDrawView(BlackjackOutputData outputData) {
                fail("This should have gone to Loss view.");
            }

            @Override
            public void prepareGameOverView(BlackjackOutputData outputData) {
                fail("This should have gone to Loss view.");
            }

            @Override
            public void prepareLossView(BlackjackOutputData outputData) {
                assertTrue(blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.HUMAN_PLAYER)
                        < blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.DEALER) &
                        blackjackRoomDao.getRoom().getPlayerBankroll(BlackjackRoom.HUMAN_PLAYER)
                                >= blackjackRoomDao.getRoom().getMinimumBet());
            }
        };

        HoldInputBoundary interactor = new HoldInteractor(apiMockDao, blackjackRoomDao, presenter);
        interactor.execute();
    }
}
