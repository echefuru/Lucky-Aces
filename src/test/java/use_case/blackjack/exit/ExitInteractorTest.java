package use_case.blackjack.exit;

import data_access.BlackjackRoomDataAccessObject;
import entity.Deck;
import org.junit.jupiter.api.Test;
import use_case.BlackjackRoomDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

public class ExitInteractorTest {

    @Test
    void switchTest() {
        BlackjackRoomDataAccessInterface blackjackRoomDao = new BlackjackRoomDataAccessObject();

        // For the success test, we need to add a BlackjackRoom to the data access object first to dispose of.
        blackjackRoomDao.createRoom(new Deck("test_id", 0), 0, 0);

        // This creates a successPresenter that tests whether the Room entity is removed from DAO.
        ExitOutputBoundary successPresenter = new ExitOutputBoundary() {
            @Override
            public void switchToGameLibraryView() {
                assertNull(blackjackRoomDao.getRoom());
            }
        };

        ExitInputBoundary interactor = new ExitInteractor(blackjackRoomDao, successPresenter);
        interactor.switchToGameLibraryView();
    }
}
