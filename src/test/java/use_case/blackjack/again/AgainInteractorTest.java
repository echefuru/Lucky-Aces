package use_case.blackjack.again;

import data_access.BlackjackRoomDataAccessObject;
import entity.Card;
import entity.Deck;
import entity.Rank;
import entity.Suit;
import org.junit.jupiter.api.Test;
import use_case.BlackjackRoomDataAccessInterface;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgainInteractorTest {

    @Test
    void executionTest() {
        BlackjackRoomDataAccessInterface blackjackRoomDao = new BlackjackRoomDataAccessObject();

        // For the success test, we need to add a BlackjackRoom to the data access object first.
        blackjackRoomDao.createRoom(new Deck("test_id", 0), 0, 0);
        blackjackRoomDao.getRoom().getHumanPlayer().setCards((new ArrayList<>(Arrays.asList(
                new Card(Rank.EIGHT, Suit.SPADES, "8S"),
                new Card(Rank.KING, Suit.HEARTS, "KH")))));
        blackjackRoomDao.getRoom().getDealer().setCards((new ArrayList<>(Arrays.asList(
                new Card(Rank.EIGHT, Suit.HEARTS, "8H"),
                new Card(Rank.KING, Suit.SPADES, "KS")))));

        // This creates a successPresenter that tests whether the player hands are reset in Entity.
        AgainOutputBoundary successPresenter = new AgainOutputBoundary() {
            @Override
            public void execute() {
                assertTrue(blackjackRoomDao.getRoom().getHumanPlayer().getCards().isEmpty());
                assertTrue(blackjackRoomDao.getRoom().getDealer().getCards().isEmpty());
            }
        };

        AgainInputBoundary interactor = new AgainInteractor(blackjackRoomDao, successPresenter);
        interactor.execute();
    }
}
