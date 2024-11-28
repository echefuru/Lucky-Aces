package use_case.blackjack;

import entity.Card;
import entity.Deck;
import entity.Rank;
import use_case.ApiDataAccessInterface;
import use_case.BlackjackRoomDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * The Blackjack Interactor.
 */
public class BlackjackInteractor implements BlackjackInputBoundary {

    private static final int NUM_DECKS = 6;
    private static final int TWENTY_ONE = 21;
    private static final int DEALER_THRESHOLD = 17;

    private final ApiDataAccessInterface apiDao;
    private final BlackjackRoomDataAccessInterface blackjackRoomDao;
    private final BlackjackOutputBoundary blackjackPresenter;

    public BlackjackInteractor(ApiDataAccessInterface apiDataAccessObject,
                               BlackjackRoomDataAccessInterface blackjackRoomDataAccessObject,
                               BlackjackOutputBoundary blackjackOutputBoundary) {
        this.apiDao = apiDataAccessObject;
        this.blackjackRoomDao = blackjackRoomDataAccessObject;
        this.blackjackPresenter = blackjackOutputBoundary;
    }

    @Override
    public void play() {
        // TODO: Entity and DAO impl below
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
            blackjackPresenter.prepare21View(blackjackOutputData);
            // TODO: Remove after testing.
            System.out.println("Instant 21.");
        }
        else {
            // If player has less than 21, they get chance to hit.
            blackjackPresenter.preparePlayView(blackjackOutputData);
        }
    }

    @Override
    public void hit() {
        // TODO: Entity and DAO impl below!
        // Player draws card.
        blackjackRoomDao.getRoom().playerDraw(apiDao.draw(blackjackRoomDao.getRoom().getDeck(), 1));

        // Cards to Strings for output.
        final List<String> playerStrings = blackjackRoomDao.getRoom().playerStrings();
        final List<String> dealerStrings = blackjackRoomDao.getRoom().dealerStrings();
        final int playerTotal = blackjackRoomDao.getRoom().getPlayerTotal();
        final BlackjackOutputData blackjackOutputData = new BlackjackOutputData(playerStrings, playerTotal,
                dealerStrings);

        // Stage set based on player hand value.
        if (playerTotal == TWENTY_ONE) {
            blackjackPresenter.prepare21View(blackjackOutputData);
        }
        else if (playerTotal < TWENTY_ONE) {
            // If player has less than 21, they get chance to hit again.
            blackjackPresenter.preparePlayView(blackjackOutputData);
        }
        else {
            // If player has more than 21, they bust.
            blackjackPresenter.prepareBustView(blackjackOutputData);
        }
    }

    @Override
    public void hold() {
        // TODO: Entity and DAO impl below!
        // Dealer draws cards.
        do {
            blackjackRoomDao.getRoom().dealerDraw(apiDao.draw(blackjackRoomDao.getRoom().getDeck(), 1));
        } while (blackjackRoomDao.getRoom().getDealerTotal() < DEALER_THRESHOLD);

        // Cards to Strings for output.
        final List<String> playerStrings = blackjackRoomDao.getRoom().playerStrings();
        final List<String> dealerStrings = blackjackRoomDao.getRoom().dealerStrings();
        final int playerTotal = blackjackRoomDao.getRoom().getPlayerTotal();
        final int dealerTotal = blackjackRoomDao.getRoom().getDealerTotal();
        final BlackjackOutputData blackjackOutputData = new BlackjackOutputData(playerStrings,
                playerTotal, dealerStrings);

        if (dealerTotal > TWENTY_ONE || dealerTotal < playerTotal) {
            // If dealer busts or has less than player, you win.
            blackjackPresenter.prepareWinView(blackjackOutputData);
        }
        else if (dealerTotal == playerTotal) {
            blackjackPresenter.prepareDrawView(blackjackOutputData);
        }
        else {
            blackjackPresenter.prepareLossView(blackjackOutputData);
        }
    }

    @Override
    public void playAgain() {
        // TODO: nullifying objects moved to entity.
        blackjackRoomDao.resetRoom();
        blackjackPresenter.preparePlayAgainView();
    }

    @Override
    public void switchToGameLibraryView() {
        blackjackRoomDao.resetRoom();
        blackjackPresenter.switchToGameLibraryView();
    }
}
