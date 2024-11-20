package interface_adapter.gamelibrary;

import interface_adapter.change_password.ChangePasswordState;
import use_case.gamelibrary.GameLibraryInputBoundary;
import use_case.gamelibrary.GameLibraryInputData;

/**
 * Controller for the game library Use Case.
 */
public class GameLibraryController {
    private final GameLibraryInputBoundary gameLibraryUseCaseInteractor;

    public GameLibraryController(GameLibraryInputBoundary gamelibraryUseCaseInteractor) {
        this.gameLibraryUseCaseInteractor = gamelibraryUseCaseInteractor;
    }

    /**
     * Executes the game library Use Case.
     * @param selectedGame the names of the available games to select from
     */
    public void execute(String selectedGame) {
        final GameLibraryInputData gameLibraryInputData = new GameLibraryInputData(selectedGame);
        gameLibraryUseCaseInteractor.execute(gameLibraryInputData);
    }

    /**
     * Executes the "switch to ChangePasswordView" Use Case.
     * @param changePasswordState current player
     */
    public void switchToChangePasswordView(ChangePasswordState changePasswordState) {
        gameLibraryUseCaseInteractor.switchToChangePasswordView(changePasswordState);
    }

    /**
     * Executes the "search" Use Case.
     * @param info search input
     */
    public void search(String info) {
        gameLibraryUseCaseInteractor.search(info);
    }
}
