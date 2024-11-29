package use_case.blackjack.hit;

import use_case.ApiDataAccessInterface;
import use_case.BlackjackRoomDataAccessInterface;
import use_case.blackjack.BlackjackOutputData;

import java.util.List;

/**
 * The Hit Interactor for Blackjack.
 */
public class HitInteractor implements HitInputBoundary {

    private static final int TWENTY_ONE = 21;

    private final ApiDataAccessInterface apiDao;
    private final BlackjackRoomDataAccessInterface blackjackRoomDao;
    private final HitOutputBoundary hitPresenter;

    public HitInteractor(ApiDataAccessInterface apiDataAccessObject,
                          BlackjackRoomDataAccessInterface blackjackRoomDataAccessObject,
                          HitOutputBoundary hitOutputBoundary) {
        this.apiDao = apiDataAccessObject;
        this.blackjackRoomDao = blackjackRoomDataAccessObject;
        this.hitPresenter = hitOutputBoundary;
    }

    @Override
    public void execute() {
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
            hitPresenter.prepare21View(blackjackOutputData);
        }
        else if (playerTotal < TWENTY_ONE) {
            // If player has less than 21, they get chance to hit again.
            hitPresenter.preparePlayView(blackjackOutputData);
        }
        else {
            // If player has more than 21, they bust.
            hitPresenter.prepareBustView(blackjackOutputData);
        }
    }
}