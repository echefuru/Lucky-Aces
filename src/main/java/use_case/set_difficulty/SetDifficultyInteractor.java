package use_case.set_difficulty;

import java.util.List;

// import entity.player.Player;
import use_case.BlackjackRoomDataAccessInterface;

/**
 * The Set Difficulty Interactor.
 */
public class SetDifficultyInteractor implements SetDifficultyInputBoundary {

//    private final BlackjackRoomDataAccessInterface blackjackRoomDataAccessInterface;
//    private final SetDifficultyOutputBoundary setDifficultyPresenter;
//
//    public SetDifficultyInteractor(BlackjackRoomDataAccessInterface blackjackRoomDataAccessInterface,
//                                   SetDifficultyOutputBoundary setDifficultyPresenter) {
//        this.blackjackRoomDataAccessInterface = blackjackRoomDataAccessInterface;
//        this.setDifficultyPresenter = setDifficultyPresenter;
//    }
//
//    @Override
//    public void execute(SetDifficultyInputData setDifficultyInputData) {
//        final String playerID = setDifficultyInputData.getPlayerID();
//        final String roomName = setDifficultyInputData.getRoomName();
//        final String newDifficulty = setDifficultyInputData.getNewDifficulty();
//        final List<Player> curPlayers = blackjackRoomDataAccessInterface.getPlayers(roomName);
//
//        boolean executed = false;
//        for (Player player: curPlayers) {
//            if (player.getPlayerID().equals(playerID)) {
//                final SetDifficultyOutputData setDifficultyOutputData = new SetDifficultyOutputData(
//                        newDifficulty, playerID, roomName);
//                setDifficultyPresenter.prepareSuccessView(setDifficultyOutputData);
//                executed = true;
//            }
//        }
//        if (!executed) {
//            setDifficultyPresenter.prepareFailView("Unsuccessful execution, please try again.");
//        }
//    }
}
