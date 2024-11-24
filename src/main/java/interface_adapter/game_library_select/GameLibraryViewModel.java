package interface_adapter.game_library_select;

import interface_adapter.ViewModel;

/**
 * The View Model for the Logged In View.
 */
public class GameLibraryViewModel extends ViewModel<GameLibraryState> {

    public GameLibraryViewModel() {
        super("Game Library");
        setState(new GameLibraryState());
    }

}
