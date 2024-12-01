package use_case.blackjack.again;

import use_case.BlackjackRoomDataAccessInterface;

/**
 * The Again Interactor for Blackjack.
 */
public class AgainInteractor implements AgainInputBoundary {

    private final BlackjackRoomDataAccessInterface blackjackRoomDao;
    private final AgainOutputBoundary againPresenter;

    public AgainInteractor(BlackjackRoomDataAccessInterface blackjackRoomDataAccessObject,
                           AgainOutputBoundary againOutputBoundary) {
        this.blackjackRoomDao = blackjackRoomDataAccessObject;
        this.againPresenter = againOutputBoundary;
    }

    @Override
    public void execute() {
        blackjackRoomDao.newRound();
        againPresenter.execute();
    }
}
