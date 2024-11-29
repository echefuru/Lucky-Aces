package interface_adapter.blackjack;

import use_case.blackjack.exit.ExitInputBoundary;

/**
 * Controller for the Exit Use Case in Blackjack.
 */
public class ExitController {

    private final ExitInputBoundary exitInteractor;

    public ExitController(ExitInputBoundary exitInteractor) {
        this.exitInteractor = exitInteractor;
    }

    /**
     * Executes the Exit Use Case which sends viewManager back to GameLibraryView.
     */
    public void switchToGameLibraryView() {
        exitInteractor.switchToGameLibraryView();
    }
}

