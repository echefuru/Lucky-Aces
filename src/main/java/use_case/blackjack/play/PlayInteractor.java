package use_case.blackjack.play;

import use_case.ApiDataAccessInterface;
import use_case.BlackjackRoomDataAccessInterface;
import use_case.blackjack.BlackjackOutputData;

import java.util.List;

/**
 * The Play Interactor for Blackjack.
 */
public class PlayInteractor implements PlayInputBoundary {

    private static final int NUM_DECKS = 2;
    private static final int TWENTY_ONE = 21;

    private final ApiDataAccessInterface apiDao;
    private final BlackjackRoomDataAccessInterface blackjackRoomDao;
    private final PlayOutputBoundary playPresenter;

    public PlayInteractor(ApiDataAccessInterface apiDataAccessObject,
                               BlackjackRoomDataAccessInterface blackjackRoomDataAccessObject,
                               PlayOutputBoundary playOutputBoundary) {
        this.apiDao = apiDataAccessObject;
        this.blackjackRoomDao = blackjackRoomDataAccessObject;
        this.playPresenter = playOutputBoundary;
    }

    @Override
    public void execute() {
        // Create the new BlackjackRoom
        blackjackRoomDao.createRoom(apiDao.createDeck(NUM_DECKS));

        // Draw the initial 2 player and 1 dealer cards.
        blackjackRoomDao.getRoom().playerDraw(apiDao.draw(blackjackRoomDao.getRoom().getDeck(), 2));
        blackjackRoomDao.getRoom().dealerDraw(apiDao.draw(blackjackRoomDao.getRoom().getDeck(), 1));

        // Cards to Strings for output.
        final List<String> playerStrings = blackjackRoomDao.getRoom().playerStrings();
        final List<String> dealerStrings = blackjackRoomDao.getRoom().dealerStrings();
        final int playerTotal = blackjackRoomDao.getRoom().getPlayerTotal();
        final BlackjackOutputData blackjackOutputData = new BlackjackOutputData(playerStrings, playerTotal,
                dealerStrings);

        // Stage set based on immediate 21 or not.
        if (playerTotal == TWENTY_ONE) {
            // If player has 21 immediately, they hold by definition.
            playPresenter.prepare21View(blackjackOutputData);
        }
        else {
            // If player has less than 21, they get chance to hit.
            playPresenter.preparePlayView(blackjackOutputData);
        }
    }
}
