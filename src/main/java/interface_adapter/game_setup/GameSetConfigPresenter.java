package interface_adapter.game_setup;

import use_case.game_set_config.GameSetConfigOutputBoundary;
import use_case.game_set_config.GameSetConfigOutputData;

/**
 * The presenter for the Set Config Use Case.
 */
public class GameSetConfigPresenter implements GameSetConfigOutputBoundary {
    private final GameSetupViewModel gameSetupViewModel;

    public GameSetConfigPresenter(GameSetupViewModel gameSetupViewModel) {
        this.gameSetupViewModel = gameSetupViewModel;
    }

    @Override
    public void prepareSuccessView(GameSetConfigOutputData gameSetConfigOutputData) {
        final GameSetupState state = gameSetupViewModel.getState();
        state.setGameConfig(gameSetConfigOutputData.getConfig());
        gameSetupViewModel.setState(state);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // It shouldn't be possible for this Use Case to fail
        System.out.println(errorMessage);
    }
}
