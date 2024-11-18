package interface_adapter.game_setup;

import interface_adapter.ViewModel;

/**
 * View model for the game setup use case.
 */
public class GameSetupViewModel extends ViewModel<GameSetupState> {
    public GameSetupViewModel() {
        super("Game Setup");
        setState(new GameSetupState());
    }
}
