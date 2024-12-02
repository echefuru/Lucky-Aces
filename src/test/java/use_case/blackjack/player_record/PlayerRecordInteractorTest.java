package use_case.blackjack.player_record;

import data_access.ApiDataAccessObject;
import data_access.BlackjackRoomDataAccessObject;
import entity.*;
import entity.player.BlackjackPlayer;
import org.junit.Test;
import use_case.ApiDataAccessInterface;
import use_case.BlackjackRoomDataAccessInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerRecordInteractorTest {

    private static final String HTML_OPEN_PARAGRAPH = "<p>";
    private static final String HTML_CLOSE_PARAGRAPH = "</p>";

    @Test
    public void CreateTest() {
        ApiDataAccessInterface apiTestDao = new ApiDataAccessObject();

        BlackjackRoomDataAccessInterface blackjackRoomTestDao = new BlackjackRoomDataAccessObject();
        blackjackRoomTestDao.createRoom(apiTestDao.createDeck(2), 200, 10);
        blackjackRoomTestDao.getRoom().setHumanPlayer(new BlackjackPlayer(200));
        PlayerRecordOutputBoundary playerRecordPresenter = new PlayerRecordOutputBoundary() {
            @Override
            public void preparePlayerRecordView(PlayerRecordOutputData playerRecordOutputData) {
            }
        };

        PlayerRecordInputBoundary playerRecordInteractor = new PlayerRecordInteractor(blackjackRoomTestDao,
                playerRecordPresenter);
        playerRecordInteractor.executeCreate();

        BlackjackPlayerRecord expectedRecord = new BlackjackPlayerRecord();
        BlackjackPlayerRecord actualRecord = blackjackRoomTestDao.getRoom().getHumanPlayer().getBlackjackPlayerRecord();
        assertEquals(expectedRecord.getHandValueRecord(), actualRecord.getHandValueRecord());
        assertEquals(expectedRecord.getTotalLosses(), actualRecord.getTotalLosses());
        assertEquals(expectedRecord.getTotalRounds(), actualRecord.getTotalRounds());
        assertEquals(expectedRecord.getTotalWins(), actualRecord.getTotalWins());

    }

    @Test
    public void CheckTest() {

        ApiDataAccessInterface apiTestDao = new ApiDataAccessInterface() {
            @Override
            public Deck createDeck() {
                return new Deck("create_test_id", 0);
            }

            @Override
            public Deck createDeck(int numDecks) {
                return new Deck("create_test_id", 0);
            }

            @Override
            public List<Card> draw(Deck deck, int numCards) {
                if (numCards == 2) {
                    return new ArrayList<>(Arrays.asList(new Card(Rank.EIGHT, Suit.SPADES, "8S"),
                            new Card(Rank.TEN, Suit.HEARTS, "10H")));
                } else {
                    return new ArrayList<>(Arrays.asList(new Card(Rank.FIVE, Suit.CLUBS, "5C")));
                }
            }

            @Override
            public void shuffle(Deck deck) {
            }
        };

        BlackjackRoomDataAccessInterface blackjackRoomTestDao = new BlackjackRoomDataAccessObject();
        Deck deckTest = apiTestDao.createDeck(2);
        blackjackRoomTestDao.createRoom(deckTest, 200, 10);
        blackjackRoomTestDao.getRoom().setHumanPlayer(new BlackjackPlayer(200));
        blackjackRoomTestDao.getRoom().getHumanPlayer().setCards(apiTestDao.draw(deckTest, 2));

        PlayerRecordOutputBoundary playerRecordPresenter = new PlayerRecordOutputBoundary() {
            @Override
            public void preparePlayerRecordView(PlayerRecordOutputData playerRecordOutputData) {
                final String expected = "<html>"
                        + "<h2 style='color:blue;'>Player Statistics</h2>"
                        + HTML_OPEN_PARAGRAPH + "<strong># of Wins:</strong> "
                        + 1 + HTML_CLOSE_PARAGRAPH
                        + HTML_OPEN_PARAGRAPH + "<strong># of Losses:</strong> "
                        + 1 + HTML_CLOSE_PARAGRAPH
                        + HTML_OPEN_PARAGRAPH + "<strong># of Rounds:</strong> "
                        + 3 + HTML_CLOSE_PARAGRAPH
                        + HTML_OPEN_PARAGRAPH + "<strong>All of your hand values:</strong> "
                        + List.of(18) + HTML_CLOSE_PARAGRAPH
                        + "</html>";
                final String actual = "<html>"
                        + "<h2 style='color:blue;'>Player Statistics</h2>"
                        + HTML_OPEN_PARAGRAPH + "<strong># of Wins:</strong> "
                        + playerRecordOutputData.getTotalWins() + HTML_CLOSE_PARAGRAPH
                        + HTML_OPEN_PARAGRAPH + "<strong># of Losses:</strong> "
                        + playerRecordOutputData.getTotalLosses() + HTML_CLOSE_PARAGRAPH
                        + HTML_OPEN_PARAGRAPH + "<strong># of Rounds:</strong> "
                        + playerRecordOutputData.getTotalRounds() + HTML_CLOSE_PARAGRAPH
                        + HTML_OPEN_PARAGRAPH + "<strong>All of your hand values:</strong> "
                        + playerRecordOutputData.getHandValueRecord().toString() + HTML_CLOSE_PARAGRAPH
                        + "</html>";

                assertEquals(expected, actual);
            }
        };

        PlayerRecordInputBoundary playerRecordInteractor = new PlayerRecordInteractor(blackjackRoomTestDao,
                playerRecordPresenter);
        playerRecordInteractor.executeCreate();
        playerRecordInteractor.executeUpdate();
        playerRecordInteractor.executeRound(1);
        playerRecordInteractor.executeRound(0);
        playerRecordInteractor.executeRound(2);

        playerRecordInteractor.executeCheck();
    }

    @Test
    public void UpdateTest() {
        ApiDataAccessInterface apiTestDao = new ApiDataAccessInterface() {
            @Override
            public Deck createDeck() {
                return new Deck("create_test_id", 0);
            }

            @Override
            public Deck createDeck(int numDecks) {
                return new Deck("create_test_id", 0);
            }

            @Override
            public List<Card> draw(Deck deck, int numCards) {
                if (numCards == 2) {
                    return new ArrayList<>(Arrays.asList(new Card(Rank.EIGHT, Suit.SPADES, "8S"),
                            new Card(Rank.TEN, Suit.HEARTS, "10H")));
                } else {
                    return new ArrayList<>(Arrays.asList(new Card(Rank.FIVE, Suit.CLUBS, "5C")));
                }
            }

            @Override
            public void shuffle(Deck deck) {
            }
        };

        BlackjackRoomDataAccessInterface blackjackRoomTestDao = new BlackjackRoomDataAccessObject();
        Deck deckTest = apiTestDao.createDeck(2);
        blackjackRoomTestDao.createRoom(deckTest, 200, 10);
        blackjackRoomTestDao.getRoom().setHumanPlayer(new BlackjackPlayer(200));
        blackjackRoomTestDao.getRoom().getHumanPlayer().setCards(apiTestDao.draw(deckTest, 2));

        PlayerRecordOutputBoundary playerRecordPresenter = new PlayerRecordOutputBoundary() {
            @Override
            public void preparePlayerRecordView(PlayerRecordOutputData playerRecordOutputData) {
            }
        };

        PlayerRecordInputBoundary playerRecordInteractor = new PlayerRecordInteractor(blackjackRoomTestDao,
                playerRecordPresenter);
        playerRecordInteractor.executeCreate();
        playerRecordInteractor.executeUpdate();

        BlackjackPlayerRecord expectedRecord = new BlackjackPlayerRecord();
        expectedRecord.updateHandValueRecord(18);
        BlackjackPlayerRecord actualRecord = blackjackRoomTestDao.getRoom().getHumanPlayer().getBlackjackPlayerRecord();
        assertEquals(expectedRecord.getHandValueRecord(), actualRecord.getHandValueRecord());
    }

    @Test
    public void RoundTest() {
        ApiDataAccessInterface apiTestDao = new ApiDataAccessInterface() {
            @Override
            public Deck createDeck() {
                return new Deck("create_test_id", 0);
            }

            @Override
            public Deck createDeck(int numDecks) {
                return new Deck("create_test_id", 0);
            }

            @Override
            public List<Card> draw(Deck deck, int numCards) {
                if (numCards == 2) {
                    return new ArrayList<>(Arrays.asList(new Card(Rank.EIGHT, Suit.SPADES, "8S"),
                            new Card(Rank.TEN, Suit.HEARTS, "10H")));
                } else {
                    return new ArrayList<>(Arrays.asList(new Card(Rank.FIVE, Suit.CLUBS, "5C")));
                }
            }

            @Override
            public void shuffle(Deck deck) {
            }
        };

        BlackjackRoomDataAccessInterface blackjackRoomTestDao = new BlackjackRoomDataAccessObject();
        Deck deckTest = apiTestDao.createDeck(2);
        blackjackRoomTestDao.createRoom(deckTest, 200, 10);
        blackjackRoomTestDao.getRoom().setHumanPlayer(new BlackjackPlayer(200));


        PlayerRecordOutputBoundary playerRecordPresenter = new PlayerRecordOutputBoundary() {
            @Override
            public void preparePlayerRecordView(PlayerRecordOutputData playerRecordOutputData) {
            }
        };

        PlayerRecordInputBoundary playerRecordInteractor = new PlayerRecordInteractor(blackjackRoomTestDao,
                playerRecordPresenter);
        playerRecordInteractor.executeCreate();
        BlackjackPlayerRecord expectedRecord = new BlackjackPlayerRecord();

        // won == 1: won
        playerRecordInteractor.executeRound(1);
        // won == 0: lost
        playerRecordInteractor.executeRound(0);
        // won == 2: draw
        playerRecordInteractor.executeRound(2);

        expectedRecord.recordRound(1);
        expectedRecord.recordRound(0);
        expectedRecord.recordRound(2);

        BlackjackPlayerRecord actualRecord = blackjackRoomTestDao.getRoom().getHumanPlayer().getBlackjackPlayerRecord();

        assertEquals(expectedRecord.getTotalWins(), actualRecord.getTotalWins());
        assertEquals(expectedRecord.getTotalLosses(), actualRecord.getTotalLosses());
        assertEquals(expectedRecord.getTotalRounds(), actualRecord.getTotalRounds());
    }
}
