package use_case.set_difficulty;

import java.util.List;

import entity.player.Player;

/**
 * The Set Difficulty Interactor.
 */
public class SetDifficultyInteractor implements SetDifficultyInputBoundary {

    private final SetDifficultyDataAccessInterface setDifficultyDataAccessInterface;
    private final SetDifficultyOutputBoundary setDifficultyPresenter;

    public SetDifficultyInteractor(SetDifficultyDataAccessInterface setDifficultyDataAccessInterface,
                                   SetDifficultyOutputBoundary setDifficultyPresenter) {
        this.setDifficultyDataAccessInterface = setDifficultyDataAccessInterface;
        this.setDifficultyPresenter = setDifficultyPresenter;
    }

    @Override
    public void execute(SetDifficultyInputData setDifficultyInputData) {
        final String playerID = setDifficultyInputData.getPlayerID();
        final String roomName = setDifficultyInputData.getRoomName();
        final List<Player> curPlayers = setDifficultyDataAccessInterface.getPlayers(roomName);

        for (Player player: curPlayers) {
            if (player.getPlayerID().equals(playerID)) {
                final SetDifficultyOutputData setDifficultyOutputData = new SetDifficultyOutputData(
                        setDifficultyInputData.getDifficulty(), playerID, roomName);
                setDifficultyPresenter.prepareSuccessView(setDifficultyOutputData);
            }
        }

    }

    @Override
    public void switchToGameRoomView() {

    }
}
