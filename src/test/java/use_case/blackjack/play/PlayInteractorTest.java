package use_case.blackjack.play;

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

public class PlayInteractorTest {

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
                if (numCards == 2) {
                    return new ArrayList<>(Arrays.asList(new Card(Rank.ACE, Suit.SPADES, "AS"),
                            new Card(Rank.KING, Suit.HEARTS, "KH")));
                }
                else {
                    return new ArrayList<>(Arrays.asList(new Card(Rank.ACE, Suit.CLUBS, "AC")));
                }
            }

            @Override
            public void shuffle(Deck deck) {
                return;
            }
        };
        BlackjackRoomDataAccessInterface blackjackRoomDao = new BlackjackRoomDataAccessObject();

        // For the success test, we need to add a BlackjackRoom to the data access object first.
        blackjackRoomDao.createRoom(apiMockDao.createDeck(2), 100, 25);

        PlayOutputBoundary presenter = new PlayOutputBoundary() {
            @Override
            public void prepare21View(BlackjackOutputData outputData) {
                assertEquals(21, blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.HUMAN_PLAYER));
            }

            @Override
            public void preparePlayView(BlackjackOutputData outputData) {
                fail("This should have gone to 21 view.");
            }
        };

        PlayInputBoundary interactor = new PlayInteractor(apiMockDao, blackjackRoomDao, presenter);
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
                if (numCards == 2) {
                    return new ArrayList<>(Arrays.asList(new Card(Rank.ACE, Suit.SPADES, "AS"),
                            new Card(Rank.ACE, Suit.HEARTS, "AH")));
                }
                else {
                    return new ArrayList<>(Arrays.asList(new Card(Rank.ACE, Suit.CLUBS, "AC")));
                }
            }

            @Override
            public void shuffle(Deck deck) {
                return;
            }
        };
        BlackjackRoomDataAccessInterface blackjackRoomDao = new BlackjackRoomDataAccessObject();

        // For the success test, we need to add a BlackjackRoom to the data access object first.
        blackjackRoomDao.createRoom(apiMockDao.createDeck(2), 100, 25);

        PlayOutputBoundary presenter = new PlayOutputBoundary() {
            @Override
            public void prepare21View(BlackjackOutputData outputData) {
                fail("This should have gone to Play view.");
            }

            @Override
            public void preparePlayView(BlackjackOutputData outputData) {
                assertNotEquals(21, blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.HUMAN_PLAYER));
            }
        };

        PlayInputBoundary interactor = new PlayInteractor(apiMockDao, blackjackRoomDao, presenter);
        interactor.execute();
    }
}
