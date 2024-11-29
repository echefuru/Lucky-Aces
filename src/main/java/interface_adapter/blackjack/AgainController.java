package interface_adapter.blackjack;

import use_case.blackjack.again.AgainInputBoundary;

/**
 * Controller for the Again Use Case in Blackjack.
 */
public class AgainController {

    private final AgainInputBoundary againInteractor;

    public AgainController(AgainInputBoundary againInteractor) {
        this.againInteractor = againInteractor;
    }

    /**
     * Executes the Again Use Case which sends viewManager back to GameLibraryView.
     */
    public void execute() {
        againInteractor.execute();
    }
}

