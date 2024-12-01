package interface_adapter.blackjack;

import use_case.blackjack.hold.HoldInputBoundary;

/**
 * Controller for the Hold Use Case in Blackjack.
 */
public class HoldController {

    private final HoldInputBoundary holdInteractor;

    public HoldController(HoldInputBoundary holdInteractor) {
        this.holdInteractor = holdInteractor;
    }

    /**
     * Executes the Hold Use Case in Blackjack.
     */
    public void execute() {
        holdInteractor.execute();
    }
}
