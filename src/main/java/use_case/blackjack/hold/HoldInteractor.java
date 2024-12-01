package use_case.blackjack.hold;

import java.util.List;

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
            holdPresenter.prepareWinView(blackjackOutputData);
        }
        else if (dealerTotal == playerTotal) {
            holdPresenter.prepareDrawView(blackjackOutputData);
        }
        else {
            holdPresenter.prepareLossView(blackjackOutputData);
        }
    }
}
