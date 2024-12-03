package use_case.blackjack.hit;

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
import use_case.blackjack.play.PlayInputBoundary;
import use_case.blackjack.play.PlayInteractor;
import use_case.blackjack.play.PlayOutputBoundary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HitInteractorTest {

    @Test
    void twentyOneTest() {
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
                return new ArrayList<>(Arrays.asList(new Card(Rank.ACE, Suit.SPADES, "AS"),
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

        HitOutputBoundary presenter = new HitOutputBoundary() {
            @Override
            public void prepare21View(BlackjackOutputData outputData) {
                assertEquals(21, blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.HUMAN_PLAYER));
            }

            @Override
            public void preparePlayView(BlackjackOutputData outputData) {
                fail("This should have gone to 21 view.");
            }

            @Override
            public void prepareGameOverView(BlackjackOutputData outputData) {
                fail("This should have gone to 21 view.");
            }

            @Override
            public void prepareBustView(BlackjackOutputData outputData) {
                fail("This should have gone to 21 view.");
            }
        };

        HitInputBoundary interactor = new HitInteractor(apiMockDao, blackjackRoomDao, presenter);
        interactor.execute();
    }

    @Test
    void playTest() {
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
                return new ArrayList<>(Arrays.asList(new Card(Rank.ACE, Suit.SPADES, "AS"),
                        new Card(Rank.TWO, Suit.HEARTS, "2H")));
            }

            @Override
            public void shuffle(Deck deck) {
                return;
            }
        };
        BlackjackRoomDataAccessInterface blackjackRoomDao = new BlackjackRoomDataAccessObject();

        // For the success test, we need to add a BlackjackRoom to the data access object first.
        blackjackRoomDao.createRoom(apiMockDao.createDeck(2), 100, 25);

        HitOutputBoundary presenter = new HitOutputBoundary() {
            @Override
            public void prepare21View(BlackjackOutputData outputData) {
                fail("This should have gone to Play view.");
            }

            @Override
            public void preparePlayView(BlackjackOutputData outputData) {
                assertNotEquals(21, blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.HUMAN_PLAYER));

            }

            @Override
            public void prepareGameOverView(BlackjackOutputData outputData) {
                fail("This should have gone to Play view.");
            }

            @Override
            public void prepareBustView(BlackjackOutputData outputData) {
                fail("This should have gone to Play view.");
            }
        };

        HitInputBoundary interactor = new HitInteractor(apiMockDao, blackjackRoomDao, presenter);
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
                        new Card(Rank.KING, Suit.HEARTS, "KH"),
                        new Card(Rank.KING, Suit.CLUBS, "KC")));
            }

            @Override
            public void shuffle(Deck deck) {
                return;
            }
        };
        BlackjackRoomDataAccessInterface blackjackRoomDao = new BlackjackRoomDataAccessObject();

        // For the success test, we need to add a BlackjackRoom to the data access object first.
        blackjackRoomDao.createRoom(apiMockDao.createDeck(2), 0, 25);

        HitOutputBoundary presenter = new HitOutputBoundary() {
            @Override
            public void prepare21View(BlackjackOutputData outputData) {
                fail("This should have gone to Game Over view.");
            }

            @Override
            public void preparePlayView(BlackjackOutputData outputData) {
                fail("This should have gone to Game Over view.");

            }

            @Override
            public void prepareGameOverView(BlackjackOutputData outputData) {
                assertTrue(21 < blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.HUMAN_PLAYER) &
                        blackjackRoomDao.getRoom().getPlayerBankroll(BlackjackRoom.HUMAN_PLAYER)
                                < blackjackRoomDao.getRoom().getMinimumBet());
            }

            @Override
            public void prepareBustView(BlackjackOutputData outputData) {
                fail("This should have gone to Game Over view.");
            }
        };

        HitInputBoundary interactor = new HitInteractor(apiMockDao, blackjackRoomDao, presenter);
        interactor.execute();
    }

    @Test
    void bustTest() {
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
                        new Card(Rank.KING, Suit.CLUBS, "KC")));
            }

            @Override
            public void shuffle(Deck deck) {
                return;
            }
        };
        BlackjackRoomDataAccessInterface blackjackRoomDao = new BlackjackRoomDataAccessObject();

        // For the success test, we need to add a BlackjackRoom to the data access object first.
        blackjackRoomDao.createRoom(apiMockDao.createDeck(2), 100, 25);

        HitOutputBoundary presenter = new HitOutputBoundary() {
            @Override
            public void prepare21View(BlackjackOutputData outputData) {
                fail("This should have gone to Bust view.");
            }

            @Override
            public void preparePlayView(BlackjackOutputData outputData) {
                fail("This should have gone to Bust view.");

            }

            @Override
            public void prepareGameOverView(BlackjackOutputData outputData) {
                fail("This should have gone to Bust view.");
            }

            @Override
            public void prepareBustView(BlackjackOutputData outputData) {
                assertTrue(21 < blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.HUMAN_PLAYER) &
                        blackjackRoomDao.getRoom().getPlayerBankroll(BlackjackRoom.HUMAN_PLAYER)
                                >= blackjackRoomDao.getRoom().getMinimumBet());
            }
        };

        HitInputBoundary interactor = new HitInteractor(apiMockDao, blackjackRoomDao, presenter);
        interactor.execute();
    }

    @Test
    void aceAsOneTest() {
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
                return new ArrayList<>(Arrays.asList(new Card(Rank.TWO, Suit.SPADES, "2S"),
                        new Card(Rank.KING, Suit.HEARTS, "KH"),
                        new Card(Rank.ACE, Suit.CLUBS, "AC")));
            }

            @Override
            public void shuffle(Deck deck) {
                return;
            }
        };
        BlackjackRoomDataAccessInterface blackjackRoomDao = new BlackjackRoomDataAccessObject();

        // For the success test, we need to add a BlackjackRoom to the data access object first.
        blackjackRoomDao.createRoom(apiMockDao.createDeck(2), 100, 25);

        HitOutputBoundary presenter = new HitOutputBoundary() {

            @Override
            public void preparePlayView(BlackjackOutputData blackjackOutputData) {
                assertEquals(13, blackjackOutputData.getPlayerTotal());
            }

            @Override
            public void prepare21View(BlackjackOutputData blackjackOutputData) {
                fail("This should have gone to Play view.");
            }

            @Override
            public void prepareBustView(BlackjackOutputData blackjackOutputData) {
                fail("This should have gone to Play view.");
            }

            @Override
            public void prepareGameOverView(BlackjackOutputData blackjackOutputData) {
                fail("This should have gone to Play view.");
            }
        };

        HitInputBoundary interactor = new HitInteractor(apiMockDao, blackjackRoomDao, presenter);
        interactor.execute();
    }

    @Test
    void aceAsElevenTest() {
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
                return new ArrayList<>(Arrays.asList(new Card(Rank.TWO, Suit.SPADES, "2S"),
                        new Card(Rank.ACE, Suit.CLUBS, "AC")));
            }

            @Override
            public void shuffle(Deck deck) {
                return;
            }
        };
        BlackjackRoomDataAccessInterface blackjackRoomDao = new BlackjackRoomDataAccessObject();

        // For the success test, we need to add a BlackjackRoom to the data access object first.
        blackjackRoomDao.createRoom(apiMockDao.createDeck(2), 100, 25);

        HitOutputBoundary presenter = new HitOutputBoundary() {

            @Override
            public void preparePlayView(BlackjackOutputData blackjackOutputData) {
                assertEquals(13, blackjackOutputData.getPlayerTotal());
            }

            @Override
            public void prepare21View(BlackjackOutputData blackjackOutputData) {
                fail("This should have gone to Play view.");
            }

            @Override
            public void prepareBustView(BlackjackOutputData blackjackOutputData) {
                fail("This should have gone to Play view.");
            }

            @Override
            public void prepareGameOverView(BlackjackOutputData blackjackOutputData) {
                fail("This should have gone to Play view.");
            }
        };

        HitInputBoundary interactor = new HitInteractor(apiMockDao, blackjackRoomDao, presenter);
        interactor.execute();
    }
}
