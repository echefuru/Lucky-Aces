package interface_adapter.game_setup;

import interface_adapter.ViewModel;

/**
 * View model for the game setup use case.
 */
public class GameSetupViewModel extends ViewModel<GameSetupState> {

    private static final String VIEW_NAME = "game_start";

    public GameSetupViewModel() {
        super(VIEW_NAME);
        setState(new GameSetupState());
    }
}
