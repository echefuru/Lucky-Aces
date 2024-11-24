package interface_adapter.game_library_select;

import use_case.game_library.GameLibraryInputBoundary;

/**
 * Controller for the program initialization use case.
 */
public class GameLibraryController {

    private final GameLibraryInputBoundary initializationInteractor;

    public GameLibraryController(GameLibraryInputBoundary initializationInteractor) {
        this.initializationInteractor = initializationInteractor;
    }

    /**
     * Execute the game Initialization Use Case.
     */
    public void execute() {
        initializationInteractor.execute();
    }
}
