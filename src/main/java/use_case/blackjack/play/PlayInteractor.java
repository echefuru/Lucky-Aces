package use_case.blackjack.play;

import java.util.List;

import entity.room.BlackjackRoom;
import use_case.ApiDataAccessInterface;
import use_case.BlackjackRoomDataAccessInterface;
import use_case.blackjack.BlackjackOutputData;

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
        // Reset the room to the state of a new round.
        blackjackRoomDao.getRoom().newRound();

        // Update the player's bankroll (assuming always minimum bet)
        final int currentBet = blackjackRoomDao.getRoom().getMinimumBet();
        blackjackRoomDao.getRoom().playerPlaceBet(BlackjackRoom.HUMAN_PLAYER, currentBet);

        // Draw the initial 2 player and 1 dealer cards.
        blackjackRoomDao.getRoom().playerDrawCards(BlackjackRoom.HUMAN_PLAYER,
                apiDao.draw(blackjackRoomDao.getRoom().getDeck(), 2));
        blackjackRoomDao.getRoom().playerDrawCards(BlackjackRoom.DEALER,
                apiDao.draw(blackjackRoomDao.getRoom().getDeck(), 1));

        // Cards to Strings for output.
        final List<String> playerStrings = blackjackRoomDao.getRoom().getPlayerCardStrings(BlackjackRoom.HUMAN_PLAYER);
        final List<String> dealerStrings = blackjackRoomDao.getRoom().getPlayerCardStrings(BlackjackRoom.DEALER);
        final int playerTotal = blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.HUMAN_PLAYER);
        final int playerBankroll = blackjackRoomDao.getRoom().getPlayerBankroll(BlackjackRoom.HUMAN_PLAYER);
        final BlackjackOutputData blackjackOutputData = new BlackjackOutputData(playerStrings, playerTotal,
                dealerStrings);
        blackjackOutputData.setPlayerBankroll(playerBankroll);
        blackjackOutputData.setCurrentBet(currentBet);

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
