package interface_adapter.set_difficulty;

import use_case.set_difficulty.SetDifficultyInputBoundary;
import use_case.set_difficulty.SetDifficultyInputData;

/**
 * Controller for the set difficulty use case.
 */
public class SetDifficultyController {
    private final SetDifficultyInputBoundary setDifficultyUseCaseInteractor;

    public SetDifficultyController(SetDifficultyInputBoundary setDifficultyUseCaseInteractor) {
        this.setDifficultyUseCaseInteractor = setDifficultyUseCaseInteractor;
    }

    /**
     * Executes the set difficulty Use Case.
     * @param newDifficulty the new difficulty set by the player.
     * @param playerID the playerID
     * @param roomName the roomName
     */
    public void execute(String newDifficulty, String playerID, String roomName) {
        final SetDifficultyInputData setDifficultyInputData = new SetDifficultyInputData(newDifficulty, playerID,
                roomName);
        // setDifficultyUseCaseInteractor.execute(setDifficultyInputData);
    }
}
