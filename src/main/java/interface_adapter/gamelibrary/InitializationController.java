package interface_adapter.gamelibrary;

import use_case.initialization.InitializationInputBoundary;

/**
 * Controller for the program initialization use case.
 */
public class InitializationController {

    private final InitializationInputBoundary initializationInteractor;

    public InitializationController(InitializationInputBoundary initializationInteractor) {
        this.initializationInteractor = initializationInteractor;
    }

    /**
     * Execute the game Initialization Use Case.
     */
    public void execute() {
        initializationInteractor.execute();
    }
}
