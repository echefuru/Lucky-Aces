package use_case.set_difficulty;

/**
 * The output boundary for the Set Difficulty Use Case.
 */
public interface SetDifficultyOutputBoundary {
    /**
     * Prepares the success view for the Set Difficulty Use Case. == switchToGameRoomView
     * @param setDifficultyOutputData the output data
     */
    void prepareSuccessView(SetDifficultyOutputData setDifficultyOutputData);

    /**
     * Prepares the failure view for the Set Difficulty Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

//    /**
//     * Switches to the GameRoom View.
//     */
//    void switchToGameRoomView();
}
