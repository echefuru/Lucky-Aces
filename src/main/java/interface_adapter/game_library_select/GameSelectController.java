package interface_adapter.game_library_select;

import use_case.game_select.GameSelectInputBoundary;
import use_case.game_select.GameSelectInputData;

/**
 * Controller for the game library Use Case.
 */
public class GameSelectController {
    private final GameSelectInputBoundary gameLibraryUseCaseInteractor;

    public GameSelectController(GameSelectInputBoundary gamelibraryUseCaseInteractor) {
        this.gameLibraryUseCaseInteractor = gamelibraryUseCaseInteractor;
    }

    /**
     * Executes the game library Use Case.
     * @param selectedGame the names of the available games to select from
     */
    public void execute(String selectedGame) {
        final GameSelectInputData gameSelectInputData = new GameSelectInputData(selectedGame);
        gameLibraryUseCaseInteractor.execute(gameSelectInputData);
    }

//    /**
//     * Executes the "search" Use Case.
//     * @param info search input
//     */
//    public void search(String info) {
//        gameLibraryUseCaseInteractor.search(info);
//    }
}
