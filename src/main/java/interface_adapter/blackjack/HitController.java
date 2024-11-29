package interface_adapter.blackjack;

import use_case.blackjack.hit.HitInputBoundary;

/**
 * Controller for the Hit Use Case in Blackjack.
 */
public class HitController {

    private final HitInputBoundary hitInteractor;

    public HitController(HitInputBoundary hitInteractor) {
        this.hitInteractor = hitInteractor;
    }

    /**
     * Executes the Hit Use Case in Blackjack.
     */
    public void execute() {
        hitInteractor.execute();
    }
}
