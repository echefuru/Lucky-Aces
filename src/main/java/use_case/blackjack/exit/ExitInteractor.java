package use_case.blackjack.exit;

import use_case.BlackjackRoomDataAccessInterface;

/**
 * The Exit Interactor for Blackjack.
 */
public class ExitInteractor implements ExitInputBoundary {

    private final BlackjackRoomDataAccessInterface blackjackRoomDao;
    private final ExitOutputBoundary exitPresenter;

    public ExitInteractor(BlackjackRoomDataAccessInterface blackjackRoomDataAccessObject,
                          ExitOutputBoundary exitOutputBoundary) {
        this.blackjackRoomDao = blackjackRoomDataAccessObject;
        this.exitPresenter = exitOutputBoundary;
    }

    @Override
    public void switchToGameLibraryView() {
        blackjackRoomDao.resetRoom();
        exitPresenter.switchToGameLibraryView();
    }
}
