package interface_adapter.game_library_select;

import interface_adapter.ViewModel;

/**
 * The View Model for the Logged In View.
 */
public class GameLibraryViewModel extends ViewModel<GameLibraryState> {

    private static final String VIEW_NAME = "game_library";

    public GameLibraryViewModel() {
        super(VIEW_NAME);
        setState(new GameLibraryState());
    }

}
