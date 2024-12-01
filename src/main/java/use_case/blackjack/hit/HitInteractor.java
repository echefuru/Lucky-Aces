package use_case.blackjack.hit;

import java.util.List;

import entity.room.BlackjackRoom;
import use_case.ApiDataAccessInterface;
import use_case.BlackjackRoomDataAccessInterface;
import use_case.blackjack.BlackjackOutputData;

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
        blackjackRoomDao.getRoom().playerDrawCards(BlackjackRoom.HUMAN_PLAYER,
                apiDao.draw(blackjackRoomDao.getRoom().getDeck(), 1));

        // Cards to Strings for output.
        final List<String> playerStrings = blackjackRoomDao.getRoom().getPlayerCardStrings(BlackjackRoom.HUMAN_PLAYER);
        final List<String> dealerStrings = blackjackRoomDao.getRoom().getPlayerCardStrings(BlackjackRoom.DEALER);
        final int playerTotal = blackjackRoomDao.getRoom().getPlayerHandValue(BlackjackRoom.HUMAN_PLAYER);
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
            blackjackRoomDao.getRoom().incrementLosses();
            blackjackOutputData.setLosses(blackjackRoomDao.getRoom().getLosses());
            hitPresenter.prepareBustView(blackjackOutputData);
        }
    }
}
