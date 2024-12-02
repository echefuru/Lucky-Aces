package use_case.game_set_config;

/**
 * The output boundary for the Set Config Use Case.
 */
public interface GameSetConfigOutputBoundary {

    /**
     * Prepares the success view for the Set Config Use Case.
     * @param gameSetConfigOutputData the output data for this use case
     */
    void prepareSuccessView(GameSetConfigOutputData gameSetConfigOutputData);

    /**
     * Prepares the fail view for the Set Config Use Case.
     * @param errorMessage the error message to be displayed
     */
    void prepareFailView(String errorMessage);

    /**
     * Prepares the config panel.
     * @param gameSetConfigOutputData the output data for this use case
     */
    void showConfigView(GameSetConfigOutputData gameSetConfigOutputData);
}
