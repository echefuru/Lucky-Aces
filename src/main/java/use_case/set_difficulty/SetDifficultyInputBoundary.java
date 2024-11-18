package use_case.set_difficulty;

/**
 * Input Boundary for actions which are related to setting the difficulty for a computer player.
 */
public interface SetDifficultyInputBoundary {

    /**
     * Executes the set difficulty use case.
     * @param setDifficultyInputData the input data.
     */
    void execute(SetDifficultyInputData setDifficultyInputData);

}
