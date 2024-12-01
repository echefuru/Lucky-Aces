package use_case.blackjack.hold;

import java.util.List;

import entity.room.BlackjackRoom;
import use_case.ApiDataAccessInterface;
import use_case.BlackjackRoomDataAccessInterface;
import use_case.blackjack.BlackjackOutputData;

/**
 * The Hold Interactor for Blackjack.
 */
public class HoldInteractor implements HoldInputBoundary {

    private static final int TWENTY_ONE = 21;
    private static final int DEALER_THRESHOLD = 17;

    private final ApiDataAccessInterface apiDao;
    private final BlackjackRoomDataAccessInterface blackjackRoomDao;
    private final HoldOutputBoundary holdPresenter;

    public HoldInteractor(ApiDataAccessInterface apiDataAccessObject,
                          BlackjackRoomDataAccessInterface blackjackRoomDataAccessObject,
                          HoldOutputBoundary holdOutputBoundary) {
        this.apiDao = apiDataAccessObject;
        this.blackjackRoomDao = blackjackRoomDataAccessObject;
        this.holdPresenter = holdOutputBoundary;
    }

    @Override
    public void execute() {
        // Dealer draws cards.
        do {
            blackjackRoomDao.getRoom().playerDrawCards(BlackjackRoom.DEALER, apiDao.draw(blackjackRoomDao.getRoom()
                    .getDeck(), 1));
        } while (blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.DEALER) < DEALER_THRESHOLD);

        // Cards to Strings for output.
        final List<String> playerStrings = blackjackRoomDao.getRoom().getPlayerCardStrings(BlackjackRoom.HUMAN_PLAYER);
        final List<String> dealerStrings = blackjackRoomDao.getRoom().getPlayerCardStrings(BlackjackRoom.DEALER);
        final int playerTotal = blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.HUMAN_PLAYER);
        final int dealerTotal = blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.DEALER);
        final BlackjackOutputData blackjackOutputData = new BlackjackOutputData(playerStrings,
                playerTotal, dealerStrings);

        if (dealerTotal > TWENTY_ONE || dealerTotal < playerTotal) {
            // If dealer busts or has less than player, you win.
            blackjackRoomDao.getRoom().incrementWins();
            blackjackOutputData.setWins(blackjackRoomDao.getRoom().getWins());
            holdPresenter.prepareWinView(blackjackOutputData);
        }
        else if (dealerTotal == playerTotal) {
            blackjackRoomDao.getRoom().incrementDraws();
            holdPresenter.prepareDrawView(blackjackOutputData);
        }
        else {
            blackjackRoomDao.getRoom().incrementLosses();
            blackjackOutputData.setLosses(blackjackRoomDao.getRoom().getLosses());
            holdPresenter.prepareLossView(blackjackOutputData);
        }
    }
}
