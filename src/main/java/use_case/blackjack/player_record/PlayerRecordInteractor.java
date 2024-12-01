package use_case.blackjack.player_record;

import java.util.List;

import entity.BlackjackPlayerRecord;
import use_case.BlackjackRoomDataAccessInterface;

/**
 * The Player Record Interactor of Blackjack.
 */
public class PlayerRecordInteractor implements PlayerRecordInputBoundary {

    private final BlackjackRoomDataAccessInterface blackjackRoomDao;
    private final PlayerRecordOutputBoundary playerRecordPresenter;

    public PlayerRecordInteractor(BlackjackRoomDataAccessInterface blackjackRoomDao, PlayerRecordOutputBoundary playerRecordPresenter) {
        this.blackjackRoomDao = blackjackRoomDao;
        this.playerRecordPresenter = playerRecordPresenter;
    }

    @Override
    public void execute() {
        final BlackjackPlayerRecord playerRecord = blackjackRoomDao.getBlackjackPlayerRecord();
        final List<Integer> handValRecord = playerRecord.getHandValueRecord();
        final int totalWins = playerRecord.getTotalWins();
        final int totalRounds = playerRecord.getTotalRounds();
        final PlayerRecordOutputData outputData = new PlayerRecordOutputData(totalWins, totalRounds, handValRecord);
        playerRecordPresenter.preparePlayerRecordView(outputData);
    }
}
