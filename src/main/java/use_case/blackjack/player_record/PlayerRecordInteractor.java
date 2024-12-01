package use_case.blackjack.player_record;

import java.util.List;

import entity.BlackjackPlayerRecord;
import entity.room.BlackjackRoom;
import use_case.BlackjackRoomDataAccessInterface;

/**
 * The Player Record Interactor of Blackjack.
 */
public class PlayerRecordInteractor implements PlayerRecordInputBoundary {

    private final BlackjackRoomDataAccessInterface blackjackRoomDao;
    private final PlayerRecordOutputBoundary playerRecordPresenter;

    public PlayerRecordInteractor(BlackjackRoomDataAccessInterface blackjackRoomDao,
                                  PlayerRecordOutputBoundary playerRecordPresenter) {
        this.blackjackRoomDao = blackjackRoomDao;
        this.playerRecordPresenter = playerRecordPresenter;
    }

    @Override
    public void executeCheck() {
        final BlackjackPlayerRecord playerRecord = blackjackRoomDao.getRoom().getHumanPlayer()
                .getBlackjackPlayerRecord();
        final List<Integer> handValRecord = playerRecord.getHandValueRecord();
        final int totalWins = playerRecord.getTotalWins();
        final int totalLosses = playerRecord.getTotalLosses();
        final int totalRounds = playerRecord.getTotalRounds();
        final PlayerRecordOutputData outputData = new PlayerRecordOutputData(totalWins, totalLosses,
                totalRounds, handValRecord);
        playerRecordPresenter.preparePlayerRecordView(outputData);

    }

    @Override
    public void executeUpdate() {
        blackjackRoomDao.getRoom().getHumanPlayer().updateHandValRecord(blackjackRoomDao.getRoom()
                .getPlayerHandValue(BlackjackRoom.HUMAN_PLAYER));
    }

    @Override
    public void executeCreate() {
        blackjackRoomDao.getRoom().getHumanPlayer().createBlackjackPlayerRecord();
    }

    @Override
    public void executeRound(int won) {
        blackjackRoomDao.getRoom().getHumanPlayer().recordRound(won);
    }
}
