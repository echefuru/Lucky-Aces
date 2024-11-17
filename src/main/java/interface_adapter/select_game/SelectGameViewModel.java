package interface_adapter.select_game;

import interface_adapter.ViewModel;

/**
 * View model for the select game use case.
 */
public class SelectGameViewModel extends ViewModel<SelectGameState> {
    public SelectGameViewModel(String game) {
        super(game);
        setState(new SelectGameState());
    }
}
